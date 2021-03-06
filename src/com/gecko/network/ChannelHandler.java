package com.gecko.network;

import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.gecko.Server;
import com.gecko.model.Player;

/**
 * Handles incoming connections and messages.
 * @author Thomas Nappo
 */
public class ChannelHandler extends SimpleChannelHandler {
	
	/**
	 * Param logging.
	 */
	private static final Logger logger = Logger.getLogger(ChannelHandler.class.getName());
	
	/**
	 * Invoked when a channel connects
	 * channelConnected
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		logger.info("Connection received: " + ctx.getChannel().getRemoteAddress());
	}
	
	/**
	 * Invoked when a channel connection is dropped
	 * channelDisconnected
	 */
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		Player player = (Player) ctx.getAttachment();
		logger.info("Wasn't null." + (ctx.getAttachment() != null));
		if(player != null) {
			logger.info("Player wasn't null!");
			Server.getOnlinePlayers().remove(player);
		}
		logger.info("Connection closed: " + ctx.getChannel().getRemoteAddress());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		//e.getCause().printStackTrace();
	}

}