package com.ocean.springboot.util.helper;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;

public class ModelMapperHelper 
{
	/** Model Mapper: To map DTO to Entity and vice-versa **/
	public static ModelMapper modelMapper = new ModelMapper();
	
	static 
	{
		modelMapper = new ModelMapper();
		
		modelMapper.addConverter(new Converter<Date, Long>()
		{
			public Long convert(MappingContext<Date, Long> context)
			{
                if(context.getSource() == null) return null;
                return context.getSource().getTime();
            }
		});

		modelMapper.addConverter(new Converter<Long, Date>()
		{
            public Date convert(MappingContext<Long, Date> context)
            {
                if(context.getSource() == null) return null;
                return  new Date(context.getSource());
            }
	    });
	        
		modelMapper.addConverter(new Converter<DateTime, Long>()
		{
            public Long convert(MappingContext<DateTime, Long> context)
            {
                if(context.getSource() == null) return null;
                return context.getSource().getMillis();
            }
        });

		modelMapper.addConverter(new Converter<Long, DateTime>()
		{
            public DateTime convert(MappingContext<Long, DateTime> context)
            {
                if(context.getSource() == null) return null;
                return  new DateTime(context.getSource());
            }
        });
		
		modelMapper.addConverter(new Converter<String, DateTime>() 
		{
            public DateTime convert(MappingContext<String, DateTime> context) 
            {
                if (context.getSource() == null) return null;
                return ISODateTimeFormat.dateTimeParser().parseDateTime(context.getSource());
            }
        });

		modelMapper.addConverter(new Converter<DateTime, String>() 
        {
            public String convert(MappingContext<DateTime, String> context) 
            {
                if (context.getSource() == null)  return null;
                DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
                return fmt.print(context.getSource());
            }
        });

		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
}
