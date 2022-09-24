# BookMyMoviesBackEnd

MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getUserDetails().getProperty("OLAFPLRD.from")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toList));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(getUserDetails().getProperty("OLAFPLRD.cc")));
			BodyPart messageBodyPart = new MimeBodyPart();
			//BodyPart attachmentBodyPart = new MimeBodyPart();
			//DataSource source = new FileDataSource(path + file);
			//attachmentBodyPart.setDataHandler(new DataHandler(source));
			//attachmentBodyPart.setFileName(file);
			message.setSubject(""+messageBody+" Olaf Data Extraction Report_"+prevDate);
			messageBodyPart.setText("Hi All," + '\n' + '\n'
					+ "Good Day., Herewith we have placed the "+messageBody+" Olaf Data Extraction for "+prevDate+" in mentioned below path. Please find the attachment."
					+ '\n' + '\n'
					+ "And the Data Count : "+countData+". Incase of any clarification please mail to # AM TSI DAN JAVACALL"
					+ '\n' + '\n'
					+ "Path :" +filepath+ ""
					+ '\n' + '\n'
					+"File Name : "+fileName+""
					+ '\n' + '\n'
					+"File Password : "+olafBean.getPassword()+""
					+ '\n' + '\n' + "Many Thanks" + '\n' + "# AM TSI DAN JAVACALL");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			//multipart.addBodyPart(attachmentBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent message successfully....");
