# spring-quartz
automated email sending spring quartz scheduler

To run this project, simply import it as gradle project.
configure your own email settings in the /src/main/resources/application.properties

write correct email address to whom you wnat to send email in property mailTo

once you run the project wait for 1 minute, cronExpression is set to 1 minute and it will send the email.
