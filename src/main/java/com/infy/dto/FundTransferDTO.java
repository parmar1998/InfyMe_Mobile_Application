package com.infy.dto;

import java.math.BigDecimal;

public class FundTransferDTO {
	private Long senderAccountId;
	private Long receiverAccountId;
	private BigDecimal transferAmount;

	public FundTransferDTO() {
		super();
	}

	public FundTransferDTO(Long senderAccountId, Long receiverAccountId, BigDecimal transferAmount) {
		super();
		this.senderAccountId = senderAccountId;
		this.receiverAccountId = receiverAccountId;
		this.transferAmount = transferAmount;
	}

	@Override
	public String toString() {
		return "FundTransferDTO [senderAccountId=" + senderAccountId + ", receiverAccountId=" + receiverAccountId
				+ ", transferAmount=" + transferAmount + "]";
	}

	public Long getSenderAccountId() {
		return senderAccountId;
	}

	public void setSenderAccountId(Long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}

	public Long getReceiverAccountId() {
		return receiverAccountId;
	}

	public void setReceiverAccountId(Long receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}
}
