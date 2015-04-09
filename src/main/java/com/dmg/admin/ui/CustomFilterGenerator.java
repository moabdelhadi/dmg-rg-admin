package com.dmg.admin.ui;

import java.io.Serializable;
import java.sql.Timestamp;

import org.tepi.filtertable.FilterGenerator;
import org.tepi.filtertable.datefilter.DateInterval;

import com.dmg.core.bean.PayEnum;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Not;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Field;

public class CustomFilterGenerator implements FilterGenerator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1903426109995747759L;

	@Override
	public Filter generateFilter(Object propertyId, Object value) {
		if (value instanceof DateInterval) {
			DateInterval interval = (DateInterval) value;
			Comparable<?> actualFrom = interval.getFrom(), actualTo = interval.getTo();
			actualFrom = actualFrom == null ? null : new Timestamp(interval.getFrom().getTime());
			actualTo = actualTo == null ? null : new Timestamp(interval.getTo().getTime());

			if (actualFrom != null && actualTo != null) {
				return new Between(propertyId, actualFrom, actualTo);
			} else if (actualFrom != null) {
				return new Compare.GreaterOrEqual(propertyId, actualFrom);
			} else {
				return new Compare.LessOrEqual(propertyId, actualTo);
			}
		}

		if ("payEnum".equals(propertyId)) {
			PayEnum v = (PayEnum) value;
			if (v != null) {
				if (v == PayEnum.NOTPAYED) {
					return new Compare.Equal("lastReceivedPayReference", "");
				} else {
					return new Not(new Compare.Equal("lastReceivedPayReference", ""));

				}

			}
		}

		return null;
	}

	@Override
	public Filter generateFilter(Object propertyId, Field<?> originatingField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractField<?> getCustomFilterComponent(Object propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void filterRemoved(Object propertyId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void filterAdded(Object propertyId, Class<? extends Filter> filterType, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
