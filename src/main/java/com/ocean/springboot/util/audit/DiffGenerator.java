package com.ocean.springboot.util.audit;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.ReferenceChange;
import org.javers.core.diff.changetype.ValueChange;

import com.google.common.collect.Lists;

import org.javers.core.diff.changetype.container.ContainerChange;
import org.javers.core.diff.changetype.container.ContainerElementChange;
import org.javers.core.diff.changetype.container.ValueAdded;
import org.javers.core.diff.changetype.container.ValueRemoved;
import org.javers.core.diff.changetype.container.ElementValueChange;
import org.joda.time.DateTime;

/**
 * This utility's main functionality is:
 * Given two Objects,
 * Generate a List of Diffs
 * where each Diff has a
 * 1. Field Name
 * 2. List of Changes (needed for list comparisons, multiple add/removes)
 *
 * Uses Javers, so make sure to annotate Entities etc. for better output
 * http://javers.org/documentation/
 *
 * Notes: Refer to documentation for annotations.
 * Useful ones are @Entity and @DiffIgnore
 *
 * @author Darryl Ong
 */
public class DiffGenerator 
{
	private FieldNameMapper fieldNameMapper;

    public DiffGenerator()
    {
        this.fieldNameMapper = new DefaultFieldNameMapperImpl();
    }

    public DiffGenerator(FieldNameMapper mapper)
    {
        this.fieldNameMapper = mapper;
    }

    protected List<DiffDTO> generateDiffs(final Object oldValue, final Object newValue) 
    {
        List<DiffDTO> response = Lists.newArrayList();
        Javers javers = JaversBuilder
		                .javers()
		                .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
		                .registerCustomComparator(new JodaDateComparator(), DateTime.class)
		                .build();
        
        Diff diff = javers.compare(oldValue, newValue);
        List<Change> changes = diff.getChanges();
        for (int i = 0; i < changes.size(); i++) 
        {
            Change change = changes.get(i);

            DiffDTO diffDTO = new DiffDTO();
            if (change instanceof ContainerChange) 
            {
                processChange(diffDTO, (ContainerChange) change);
            } 
            else if (change instanceof ReferenceChange) 
            {
                processChange(diffDTO, (ReferenceChange) change);
            } 
            else if (change instanceof ValueChange) 
            {
                processChange(diffDTO, (ValueChange) change);
            }

            if (diffDTO.getFieldName() != null && diffDTO.getFieldName().length() > 0) {
                response.add(diffDTO);
            }
        }
        return response;
    }

    protected String formatPropertyValue(final String propertyValue) 
    {
        String formatted;
        if (propertyValue != null && propertyValue.length() > 0) 
        {
            formatted = propertyValue;
        } 
        else 
        {
            formatted = "null";
        }
        return formatted;
    }

    protected void processChange(final DiffDTO diffDTO, final ReferenceChange refChange) 
    {
        StringBuilder fieldBuilder = new StringBuilder();
        if (refChange.getAffectedLocalId() != null) 
        {
            fieldBuilder.append(refChange.getAffectedGlobalId() + ": ");
        }
        if (refChange.getPropertyName() != null) 
        {
            fieldBuilder.append(fieldNameMapper.getLabel(refChange.getPropertyName()));
        }
        diffDTO.setFieldName(fieldBuilder.toString());

        String leftChange = refChange.getLeft() == null ? "" : refChange.getLeftObject().toString();
        String rightChange = refChange.getRight() == null ? "" : refChange.getRightObject().toString();

        diffDTO.getChanges().add(formatPropertyValue(leftChange) + " -> " + formatPropertyValue(rightChange));
    }

    protected void processChange(final DiffDTO diffDTO, final ValueChange valChange) 
    {
        StringBuilder fieldBuilder = new StringBuilder();
        if (valChange.getAffectedLocalId() != null) 
        {
            fieldBuilder.append(valChange.getAffectedGlobalId() + ": ");
        }
        if (valChange.getPropertyName() != null) 
        {
            fieldBuilder.append(fieldNameMapper.getLabel(valChange.getPropertyName()));
        }
        diffDTO.setFieldName(fieldBuilder.toString());

        String leftChange = valChange.getLeft() == null ? "" : valChange.getLeft().toString();
        String rightChange = valChange.getRight() == null ? "" : valChange.getRight().toString();

        diffDTO.getChanges().add(formatPropertyValue(leftChange) + " -> " + formatPropertyValue(rightChange));
    }

    protected void processChange(final DiffDTO diffDTO, final ContainerChange conChange) 
    {
        StringBuilder fieldBuilder = new StringBuilder();
        if (conChange.getAffectedLocalId() != null) 
        {
            fieldBuilder.append(conChange.getAffectedGlobalId() + ": ");
        }
        if (conChange.getPropertyName() != null) 
        {
            fieldBuilder.append(fieldNameMapper.getLabel(conChange.getPropertyName()));
        }
        diffDTO.setFieldName(fieldBuilder.toString());

        for (ContainerElementChange entry : conChange.getChanges()) 
        {
            processChange(diffDTO, entry);
        }
    }

    protected void processChange(final DiffDTO diffDTO, final ContainerElementChange conElementChange) 
    {
        if (conElementChange instanceof ValueAdded)
        {
            diffDTO.getChanges().add("Added " + formatPropertyValue(((ValueAdded) conElementChange).getAddedValue().toString()));
        } 
        else if (conElementChange instanceof ValueRemoved) 
        {
            diffDTO.getChanges().add("Removed " + formatPropertyValue(((ValueRemoved) conElementChange).getRemovedValue().toString()));
        } 
        else if (conElementChange instanceof ElementValueChange) 
        {
            diffDTO.getChanges().add(((ElementValueChange) conElementChange).getLeftValue() + " -> " + ((ElementValueChange) conElementChange).getRightValue());
        }
    }
}
