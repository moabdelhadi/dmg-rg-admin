package com.dmg.admin.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.service.MeterService;
import com.dmg.admin.util.MailManager;
import com.dmg.admin.util.PropertiesManager;
import com.dmg.core.bean.MeterReading;

public class SendMeterReadingsThread implements Runnable {

	private static Logger log = LoggerFactory.getLogger(SendMeterReadingsThread.class);

	@Override
	public void run() {
		
		while (true) {
			try {
				Thread.sleep(200000);
				String emailAuh = PropertiesManager.getInstance().getProperty("gasmeter.email.auh");
				String emailDu = PropertiesManager.getInstance().getProperty("gasmeter.email.du");

				doProcess("ABUDHABI", emailAuh);
				doProcess("DUBAI", emailDu);
			} catch (InterruptedException e) {
				log.error("Error in sleep", e);
			}

		}

	}

	private void doProcess(String city, String email) {

		try {
			
			String fileBasePath = PropertiesManager.getInstance().getProperty("gasmeter.file.basepath")+city;
			
			MeterService service = new MeterService();
			Calendar cal = Calendar.getInstance();

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int DAY = cal.get(Calendar.DAY_OF_MONTH);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
			cal.setTime(dateFormat.parse(year + "-" + month + "-" + DAY));

			cal.add(Calendar.DATE, -2);

			Date from = cal.getTime();
			log.debug("from=" + from);

			cal.add(Calendar.DATE, 1);
			Date to = cal.getTime();
			log.debug("to=" + to);

			String fromString = dateFormat.format(from);
			
			String fileNamePath = fileBasePath + fromString + ".csv";
			File file = new File(fileNamePath);
			
			if(file.exists()){
				return;
			}
			
			
			

			
			
			List<MeterReading> lisReadings = service.lisReadings(city, from, to);

			if (lisReadings == null || lisReadings.size() == 0) {
				return;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("ID");
			sb.append(",");
			sb.append("DATE");
			sb.append(",");
			sb.append("CONTRACT NO");
			sb.append(",");
			sb.append("CITY");
			sb.append(",");
			sb.append("METER READING");
			sb.append(",");
			sb.append("IMG Path");
			sb.append("\n");

			for (MeterReading meterReading : lisReadings) {
				sb.append(meterReading.getId());
				sb.append(",");
				sb.append(meterReading.getCreationDate());
				sb.append(",");
				sb.append(meterReading.getContractNo());
				sb.append(",");
				sb.append(meterReading.getCity());
				sb.append(",");
				sb.append(meterReading.getMeterReading());
				sb.append(",https://pay.royalgas.com/dmg-rg-client-v2/imgs/");
				sb.append(meterReading.getImageName());
				sb.append("\n");
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamePath));
			writer.write(sb.toString());
			writer.close();

			log.info("BEFOR SEND EMAIL");
			String msgB = getMessageBody(from, to);
			MailManager.getInstance().sendMail(email, "Meter Reading" + fromString, msgB, fileNamePath);
			log.info("AFTER SEND EMAIL");

		} catch (Exception e) {
			log.error("Error in GENERAL;", e);
		}

	}

	private String getMessageBody(Date from, Date to) {
		StringBuilder builder = new StringBuilder();

		builder.append("<p>Meter Reading for the Date From: ");
		builder.append(from);
		builder.append("  TO: ");
		builder.append(to);
		builder.append("</p>");

		return builder.toString();
	}

}
