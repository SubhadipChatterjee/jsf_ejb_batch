Demo 1 (Checkpoint 1)
---------------------
01. xhtml with HTML5
02. backed Bean - link to all the fields of xhtml and injected with a No-interface EJB 3.1 Session Bean
03. Spring Batch configuration - defining reader & composite item writer [JDBC component + FlatFileComponent]
04. Configuring in Spring an embedded database (HSQL)
05. A reference datasource is used in the EJB Beans - reading or updating
06. TimerService from EJB 3.1
07. CDI Event
08. Event notifier
09. Launching Spring-Batch Job instance from a Timer Session Bean...
10. CDI event is fired upon completion (1 sec delay post launch)
11. Websocket (EE 7)
12. JSF/HTML5 script to display the batch status
13. An implementation of Spring StepExecutionListener

TO DO:
-----
1. CDI bean for JSF backing bean, instead of JSF @ManagedBean
2. @Resource to inject javax.sql.DataSource
3. Spring Batch Admin integration [useful in distributed deployment]
	/ Direct Spring Batch database integration [deploy all in one Host]
4. IBM Message Broker/MQ to manage the Job post & notify status features [useful in distributed deployment]
5. Data Trasnfer service over SFTP
