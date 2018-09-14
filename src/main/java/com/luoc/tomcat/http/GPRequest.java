package com.luoc.tomcat.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/9/14 8:07
 */
public class GPRequest {

    private  ChannelHandlerContext ctx;
    private HttpRequest r;

    public GPRequest(ChannelHandlerContext ctx, HttpRequest r) {
            this.ctx = ctx;
            this.r = r;
    }

    public  String  getUri(){
         return  r.getUri();
    }

    public  String getMethod(){
        return  r.getMethod().name();
    }

    public Map<String,List<String>> getParameters(){
        QueryStringDecoder decoder = new QueryStringDecoder(r.getUri());
        return  decoder.parameters();
    }

    public  String getParameter(String name){
        Map<String,List<String>> params = getParameters();
        List<String>  list = params.get(name);
        if (null  == list){
            return null;
        }else {
            return list.get(0);
        }
    }

}
