package chabu.tester.data;

import java.nio.ByteBuffer;

public class ResultChannelStat extends AResult {

	public final int channelId;
	public final int rxCount;
	public final int txCount;

	public ResultChannelStat(long time, int channelId, int txCount, int rxCount ) {
		super(ResultId.CHANNEL_STAT, time);
		this.channelId = channelId;
		this.rxCount = rxCount;
		this.txCount = txCount;
	}
	
	@Override
	public void encode(ByteBuffer buf) {
		super.encode(buf);
		buf.put( (byte)this.channelId );
		buf.putShort( (short)this.txCount );
		buf.putShort( (short)this.rxCount );
	}
	
	static ResultChannelStat createResultChannelStat(ByteBuffer buf) {
		long   time       = buf.getLong();
		int    channelId  = buf.get() & 0xFF;
		int    txCount    = buf.getShort() & 0xFFFF;
		int    rxCount    = buf.getShort() & 0xFFFF;
		return new ResultChannelStat(time, channelId, rxCount, txCount );
	}
	@Override
	public String toString() {
		return String.format("Result[%s %s ch:%s tx:%5s rx:%5s]", resultId, timeStr(time), channelId, txCount, rxCount );
	}

}
