package stellarium.sync;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import stellarium.StellarSky;

public class MessageInfoQuery implements IMessage {

	private int dimensionId;
	
	public MessageInfoQuery() { }
	
	public MessageInfoQuery(int dimensionId) {
		this.dimensionId = dimensionId;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.dimensionId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.dimensionId);
	}
	
	public static class MessageInfoQueryHandler implements IMessageHandler<MessageInfoQuery, IMessage> {

		@Override
		public IMessage onMessage(MessageInfoQuery message, MessageContext ctx) {
			return StellarSky.instance.getNetworkManager().onQueryInformation(
					ctx.getServerHandler().playerEntity.mcServer,
					message.dimensionId);
		}
		
	}

}