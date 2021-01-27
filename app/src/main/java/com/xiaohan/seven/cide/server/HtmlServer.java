package com.xiaohan.seven.cide.server;
import fi.iki.elonen.NanoHTTPD;

public class HtmlServer extends NanoHTTPD {
    
    private String data = new String();
    
    public HtmlServer(int port) {
        super(port);
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getData() {
        return data.toString();
    }
    
    @Override
    public Response serve(IHTTPSession session) {
        return newFixedLengthResponse(this.data);
    }
    
}
