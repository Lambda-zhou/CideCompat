/*
 * Copyright (c) 2011 Tah Wei Hoon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License Version 2.0,
 * with full text available at http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * This software is provided "as is". Use at your own risk.
 */
package com.myopicmobile.textwarrior.common;

/**
 * Singleton class containing the symbols and operators of the Javascript language
 */
public class LanguageJavascript extends Language {
	private static Language _theOne = null;
	
	private final static String[] keywords = {
			"break","case","catch","class","const",
			"continue","debugger","default","delete","do",
			"else","export","extends","finally","for",
			"function","if","import","in","instanceof",
			"new","return","final","in","float",
			"for","super","switch","this",
			"throw","try","typeof","var","void",
			"while","let","with","yield","libs_inthis",
			"true","false","null","undefined"
	};
	private final static String INTHIS = "startActivity|sendMessage|startAS|useEZ|runOnUiThread|addCallBack|" +
			"removeCallBack|clearCallBack|addOnChange|clearOnChange|removeOnChange";
	private final static char[] BASIC_OPERATORS = {
			'\t','\"','\'','(', ')', '{', '}', '[', ']', '<', '>',
			'.', ',', ';', '=', '+', '-',
			'/', '*', '&', '!', '|', ':',
			'?', '~', '%', '^'
	};
	private  final  static  String[] funtions={};
	public static Language getInstance(){
		if(_theOne == null){
			_theOne = new LanguageJavascript();
		}
		return _theOne;
	}
	
	private LanguageJavascript(){
		setKeywords(keywords);
		setOperatorlist(BASIC_OPERATORS);
		setNames(funtions);//
		addBasePackage("libs_inthis",INTHIS.split("\\|"));
	}

	public boolean isLineAStart(char c){
		return false;
	}
}
