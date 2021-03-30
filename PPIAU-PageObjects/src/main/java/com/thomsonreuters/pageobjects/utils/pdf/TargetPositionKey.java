package com.thomsonreuters.pageobjects.utils.pdf;

import org.apache.pdfbox.util.TextPosition;



public class TargetPositionKey {
	private int x;
	private int y;

	public TargetPositionKey() {
		x = 0;
		y = 0;
	}
	
	public TargetPositionKey(TextPosition targetPosition) {
		x = (int) targetPosition.getX();
		y = (int) targetPosition.getY();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TargetPositionKey other = (TargetPositionKey) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}	
}
