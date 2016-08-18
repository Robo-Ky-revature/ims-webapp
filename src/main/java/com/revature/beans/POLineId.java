package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class POLineId implements Serializable{

		@ManyToOne
		@JoinColumn(name="ORDER_NUMBER",nullable=false,unique=true)
        private PurchaseOrder order;

        @Column(name="LINE_NUMBER",nullable=false)
        private int line;

		public PurchaseOrder getOrder() {
			return order;
		}

		public void setOrder(PurchaseOrder order) {
			this.order = order;
		}

		public int getLine() {
			return line;
		}

		public void setLine(int line) {
			this.line = line;
		}

		public POLineId(PurchaseOrder order, int line) {
			super();
			this.order = order;
			this.line = line;
		}

		public POLineId() {
			super();
		}

       
}
