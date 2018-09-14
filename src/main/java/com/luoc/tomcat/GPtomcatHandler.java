package com.luoc.tomcat;

import com.luoc.tomcat.http.GPRequest;
import com.luoc.tomcat.http.GPResponse;
import com.luoc.tomcat.servlets.Myservlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/9/14 7:47
 */
public class GPtomcatHandler extends ChannelInboundHandlerAdapter {

  //  private Logger LOG = Logger.getLogger(GPTomcatHandler.class);

    private static final Map<Pattern, Class<?>> servletMapping = new HashMap<Pattern, Class<?>>();

    /*static{

    		CustomConfig.load("web.properties");

    		for (String key : CustomConfig.getKeys()) {
			if(key.startsWith("servlet")){
				String name = key.replaceFirst("servlet.", "");
				if(name.indexOf(".") != -1){
					name = name.substring(0,name.indexOf("."));
				}else{
					continue;
				}
				String pattern = CustomConfig.getString("servlet." + name + ".urlPattern");
				pattern = pattern.replaceAll("\\*", ".*");
				String className = CustomConfig.getString("servlet." + name + ".className");
				if(!servletMapping.containsKey(pattern)){
					try {
						servletMapping.put(Pattern.compile(pattern), Class.forName(className));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
    }*/

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       /* if (msg instanceof HttpRequest) {
        		HttpRequest r = (HttpRequest) msg;
        		GPRequest request = new GPRequest(ctx,r);
        		GPResponse response = new GPResponse(ctx,r);
            String uri = request.getUri();
            String method = request.getMethod();

            LOG.info(String.format("Uri:%s method %s", uri, method));

            boolean hasPattern = false;
            for (Entry<Pattern, Class<?>> entry : servletMapping.entrySet()) {
            		if (entry.getKey().matcher(uri).matches()) {
	            		GPServlet servlet = (GPServlet)entry.getValue().newInstance();
	            		if("get".equalsIgnoreCase(method)){
	            			servlet.doGet(request, response);
	            		}else{
	            			servlet.doPost(request, response);
	            		}
	            		hasPattern = true;
                }
			}

            if(!hasPattern){
                String out = String.format("404 NotFound URL%s for method %s", uri,method);
                response.write(out,404);
                return;
            }
        }  */
        if (msg instanceof HttpRequest) {
            HttpRequest r = (HttpRequest) msg;

            GPRequest request = new GPRequest(ctx, r);
            GPResponse resopnse = new GPResponse(ctx, r);

            //Myservlet.class.newInstance().ge

            new Myservlet().doGet(request, resopnse);
        }

    }
}

  /*  @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }  */
