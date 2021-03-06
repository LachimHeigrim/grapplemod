package com.yyon.grapplinghook.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.yyon.grapplinghook.grapplemod;
//* // 1.8 Compatability

/*
 * This file is part of GrappleMod.

    GrappleMod is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    GrappleMod is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with GrappleMod.  If not, see <http://www.gnu.org/licenses/>.
 */

public class GrappleEndMessage implements IMessage {
   
	public int entityid;
	public int arrowid;

    public GrappleEndMessage() { }

    public GrappleEndMessage(int entityid, int arrowid) {
    	this.entityid = entityid;
    	this.arrowid = arrowid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.entityid = buf.readInt();
    	this.arrowid = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(this.entityid);
    	buf.writeInt(this.arrowid);
    }

    public static class Handler implements IMessageHandler<GrappleEndMessage, IMessage> {
    	public class runner implements Runnable {
    		GrappleEndMessage message;
    		MessageContext ctx;
    		public runner(GrappleEndMessage message, MessageContext ctx) {
    			super();
    			this.message = message;
    			this.ctx = ctx;
    		}
    		
            @Override
            public void run() {

				int id = message.entityid;
				
				World w = ctx.getServerHandler().playerEntity.world;
				
				grapplemod.receiveGrappleEnd(id, w, message.arrowid);
            }
    	}
    	
        @Override
        public IMessage onMessage(GrappleEndMessage message, MessageContext ctx) {

        	IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.world; // or Minecraft.getMinecraft() on the client
            mainThread.addScheduledTask(new runner(message, ctx));

            return null; // no response in this case
        }
    }
}
