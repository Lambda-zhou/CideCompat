package com.xiaohan.seven.cide.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import com.myopicmobile.textwarrior.common.ColorScheme;
import com.myopicmobile.textwarrior.common.ColorSchemeDark;
import com.myopicmobile.textwarrior.common.ColorSchemeLight;
import com.myopicmobile.textwarrior.common.Document;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import com.myopicmobile.textwarrior.common.Language;
import com.myopicmobile.textwarrior.common.Lexer;
import com.myopicmobile.textwarrior.common.ReadThread;
import com.myopicmobile.textwarrior.common.ThemeLanguage;
import com.myopicmobile.textwarrior.common.To;
import com.myopicmobile.textwarrior.common.WriteThread;
import java.io.File;
import com.xiaohan.seven.cide.api.FunctionCallBACK;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2017/11/19.
 */

public class TextEditor extends FreeScrollingTextField {
    private boolean _isWordWrap;
    private String _lastSelectFile;
    private FunctionCallBACK callBACK;
    private Document _inputtingDoc;
    private int _index;
    private To to;
	private int id;
	
    public TextEditor(Context context) {
        this(context,null);
    }

    public TextEditor(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
	
    public TextEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(Typeface.MONOSPACE);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        float size= TypedValue.applyDimension(2, BASE_TEXT_SIZE_PIXELS, dm);
        setTextSize((int)size);
		setDark(true);
        Lexer.setLanguage(ThemeLanguage.getInstance());
        setShowLineNumbers(true);
        setHorizontalScrollBarEnabled(true);
        setHighlightCurrentRow(true);
        setWordWrap(false);
        setAutoIndentWidth(4);
		setBackgroundColor(0x000000);
        setTextColor(Color.BLACK);
		
    }
    public void setLanguage(Language language){
        Lexer.setLanguage(language);
        _autoCompletePanel.setLanguage(Lexer.getLanguage());
    }
    public TextEditor addBasePackage(String key,String[] values){
        Language languageJavascript = Lexer.getLanguage();
        languageJavascript.addBasePackage(key,values);
        return this;
    }
    public TextEditor addUserWord(String key){
        Language languageJavascript = Lexer.getLanguage();
        languageJavascript.addUserWord(key);
        return this;
    }
    public TextEditor updateUserWord(){
        Language languageJavascript = Lexer.getLanguage();
        languageJavascript.updateUserWord();
        return this;
    }

    public TextEditor setDark(boolean isDark) {
        if (isDark)
            setColorScheme(new ColorSchemeDark());
        else
            setColorScheme(new ColorSchemeLight());
        return this;
    }
    public void setKeywordColor(int color) {
        getColorScheme().setColor(ColorScheme.Colorable.KEYWORD, color);
    }
    public char[] getOperators(){
        return Lexer.getLanguage().getOperatorlist();
    }
    public void setStringColor(int color) {
        getColorScheme().setColor(ColorScheme.Colorable.STRING, color);
    }

    public void setCommentColor(int color) {
        getColorScheme().setColor(ColorScheme.Colorable.COMMENT, color);
    }

    public void setBackgroundColor(int color) {
        getColorScheme().setColor(ColorScheme.Colorable.BACKGROUND, color);
    }

    public void setTextColor(int color) {
        getColorScheme().setColor(ColorScheme.Colorable.FOREGROUND, color);
    }

    public void setTextHighlightColor(int color) {
        getColorScheme().setColor(ColorScheme.Colorable.SELECTION_BACKGROUND, color);
    }

    public String getSelectedText() {
        // TODO: Implement this method
        return  _hDoc.subSequence(getSelectionStart(), getSelectionEnd() - getSelectionStart()).toString();
    }


    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        final int filteredMetaState = event.getMetaState() & ~KeyEvent.META_CTRL_MASK;
        if (KeyEvent.metaStateHasNoModifiers(filteredMetaState)) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_A:
                    selectAll();
                    return true;
                case KeyEvent.KEYCODE_X:
                    cut();
                    return true;
                case KeyEvent.KEYCODE_C:
                    copy();
                    return true;
                case KeyEvent.KEYCODE_V:
                    paste();
                    return true;
            }
        }
        return super.onKeyShortcut(keyCode, event);
    }
    @Override
    public void setWordWrap(boolean enable) {
        // TODO: Implement this method
        _isWordWrap = enable;
        super.setWordWrap(enable);
    }

    public DocumentProvider getText() {
        return createDocumentProvider();
    }
    public void insert(int idx, String text) {
        selectText(false);
        moveCaret(idx);
        paste(text);
    }
    public void setText(CharSequence c, boolean isRep) {
        replaceText(0, getLength() - 1, c.toString());
    }

    public void setText(CharSequence c) {
        Document doc=new Document(this);
        doc.setWordWrap(_isWordWrap);
        doc.setText(c);
        setDocumentProvider(new DocumentProvider(doc));
    }

    public void setSelection(int index) {
        selectText(false);
        if (!hasLayout())
            moveCaret(index);
        else
            _index = index;
    }

    public void undo() {
        DocumentProvider doc = createDocumentProvider();
        int newPosition = doc.undo();

        if (newPosition >= 0) {
            //TODO editor.setEdited(false); if reached original condition of file
            setEdited(true);

            respan();
            selectText(false);
            moveCaret(newPosition);
            invalidate();
        }

    }

    public void redo() {
        DocumentProvider doc = createDocumentProvider();
        int newPosition = doc.redo();

        if (newPosition >= 0) {
            setEdited(true);

            respan();
            selectText(false);
            moveCaret(newPosition);
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // TODO: Implement this method
        super.onLayout(changed, left, top, right, bottom);
        if (_index != 0 && right > 0) {
            moveCaret(_index);
            _index = 0;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ReadThread.MSG_READ_OK:
                    setText(msg.obj.toString());
                    if (callBACK!=null)callBACK.T("open_ok");//打开成功
                    break;
                case ReadThread.MSG_READ_FAIL:
                    if (callBACK!=null)callBACK.T("open_fail");//打开失败
                    break;
                case WriteThread.MSG_WRITE_OK:
                    if (callBACK!=null)callBACK.T("save_ok");//保存成功
                    if (to!=null){
                        to.T("ok");
                    }
                    break;
                case WriteThread.MSG_WRITE_FAIL:
                    if (callBACK!=null)callBACK.T("save_fail");//保存失败
                    if (to!=null){
                        to.T("fail");
                    }
                    break;
            }
        }
    };

    public void open(String filename) {
        open(filename,null);
    }

    public void open(String filename,To to){
        if (to!=null) {
            if (isEdited()) {
                to.T("fail");
                return;
            }
        }
        _lastSelectFile = filename;

        File inputFile = new File(filename);
        _inputtingDoc = new Document(this);
        _inputtingDoc.setWordWrap(this.isWordWrap());
        ReadThread readThread = new ReadThread(inputFile.getAbsolutePath(), handler,to);
        readThread.start();

    }
    public void setCallback(FunctionCallBACK callback){
        callBACK = callback;
    }
    /**
     * 保存文件
     * * @param file
     */
    public void save(String file) {
        save(file,null);
    }

    /**
     * 保存文件
     * * @param file
     */
    public void save(String file, To toBeContinue) {
        this.to = toBeContinue;
        WriteThread writeThread=new WriteThread(getText().toString(), file, handler);
        writeThread.start();
    }
    public File getOpenedFile() {
        if (_lastSelectFile != null)
            return  new File(_lastSelectFile);

        return  null;
    }

    public void setOpenedFile(String file) {
        _lastSelectFile = file;
    }
}

