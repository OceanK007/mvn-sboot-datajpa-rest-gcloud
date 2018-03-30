package com.ocean.springboot.util.audit;

import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.custom.CustomPropertyComparator;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.property.Property;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaDateComparator implements CustomPropertyComparator<DateTime, ValueChange> 
{
    public ValueChange compare(final DateTime left, final DateTime right, final GlobalId affectedId, final Property property) 
    {
        if (left != null && right != null && (left.getMillis() == right.getMillis())) 
        {
            return null;
        }

        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");
        String leftTransform = left != null ? dtfOut.print(left) : null;
        String rightTransform = right != null ? dtfOut.print(right) : null;

        return new ValueChange(affectedId, property.getName(), leftTransform, rightTransform);
    }
}
