# Product Price Forecasting API Service

## SPDX-FileName: README.md
## SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
## SPDX-License-Identifier: Apache-2.0


# NARRATE – Bisherige Arbeiten

## Inhaltsverzeichnis

1	Überblick	1
1.1	Hosting / Infrastruktur	2
1.2	Work Package (WP) 2 - Supply Chain Disruption Risk Detection & Diagnostic Framework / Task 2.2 - Risk Identification & Processing	2
1.3	Task 4.1 – Resilience, Sustainability & Circularity Stress Testing Scenarios	3
1.4	Task 7.3 – Innovation Potential & Impact	4
2	Die Kits	4
2.1	Das Monsterkit	4
2.1.1	Der MQTT Broker	4
2.1.2	Das Java GUI	5
2.1.3	Externe Events	6
2.1.4	InfluxDB / telegraf	7
2.1.5	CEP	8
2.1.6	Der Filler	10
2.1.7	Python KI	10
2.2	Das Esper Testkit	11


## 1	Überblick
Wir haben in NARRATE bisher vier Baustellen:
1.	Hosting / Infrastruktur
2.	Work Package (WP) 2 - Supply Chain Disruption Risk Detection & Diagnostic Framework / Task 2.2 - Risk Identification & Processing
3.	Task 4.1 - Resilience, Sustainability & Circularity Stress Testing Scenarios
4.	Task 7.3 – Innovation Potential & Impact

### 1.1	Hosting / Infrastruktur
Eigentlich steht in der Arbeitsbeschreibung nichts davon, dass wir Dinge hosten. Hier steht lediglich, dass wir Cumulocity (C8y) zur Verfügung stellen. Leider erwecken wir stellenweise den Eindruck, als beinhalte das auch eine KI Umgebung. Dies stimmt jedoch nicht, C8y kann lediglich KI-Modelle ausführen, wenn sie im geeigneten Format vorliegen (ONNX). Im Kostenvoranschlag haben wir jedoch Beträge für Hosting veranschlagt und, da wir ja hierfür den Hetzner haben, spräche ja auch nichts dagegen.
Am 28.08.24 erreichte uns eine Mail von Amparo (NARRATE - changes in p/m and equipment) in der der Partner NUNSYS Teile unseres Budgets fordert, dafür, dass ohne C8y von ihnen mehr Hosting geleistet werden muss. Dieses Ansinnen wurde von uns in einer Besprechung am 20.09.24 zurückgewiesen, mit der Begründung, dass wir statt C8y genauso gut etwas anderes hosten können. In einem Deliverable (8.4) haben die beteiligten Partner sich für die FIWARE Plattform entschieden.
Seit dem Zurückweisen der Budgetforderung herrschte so etwas der Eindruck, als hätten wir uns zu jeglichem Hosting verpflichtet, das stimmt aber so nicht. Niemals haben wir eine KI-Umgebung versprochen, das muss NUNSYS schon selber tun und das wissen sie auch.  
Was wir jedoch (bisher) hosten, ist die FIWARE Plattform. Diese enthält grundsätzlich ein IoT und eine CEP (PERSEO). Im Moment stellen wir auf unserem Hetzner einen Linux Container zur Verfügung, der über das Internet erreichbar ist. Hierauf stehen schon Teile des FIWARE Stack bereit, z.B. ein Device Monitor, siehe https://narrate.sagresearch.de:3000/device/monitor.
Außer der FIWARE Infrastruktur ist eine andere Infrastruktur geplant, die den gesamten Datenbestand des Systems als ein Netz von Digital Twins (DTs) darstellt. Diese DTs sollen als RDF in einer SparkQL Datenbank gespeichert werden. Der Gedanke beunruhigt mich insofern, als da auch alle Events rein sollen. Aber vermutlich sind Events in NARRATE gar nicht so massenhaft, wie ich mir das vorstelle. Zum einen scheint es kaum Sensoren zu geben, die einem etwas über Maschinenzustände sagen könnten, zum anderen sind die Transporte teilweise fremdvergeben, so dass man hier auch nichts über unterwegs erfährt. Dieser Teil der Infrastruktur (das DT-Netz) wurde mir nur mitgeteilt. Es scheint als ob MikeP. Das alles schon vorab beschlossen hatte.              

### 1.2	Work Package (WP) 2 - Supply Chain Disruption Risk Detection & Diagnostic Framework / Task 2.2 - Risk Identification & Processing
Für dieses Work Package sind wir verantwortlich, obwohl wir hier nur eine Task habe nämlich Task 2.2 Risk Identification & Processing. Obwohl diese Task gerade erst losgelaufen ist (01.12.25) habe ich hier schon was getan und auch gebucht, schon allein, weil sowohl für unsere F2F als auch für unser Review am 27.09.2024 dafür Timeslots vorgesehen waren. 
Um sich ein wie von uns avisiertes System zum Verwalten von Lieferketten überhaupt vorstellen zu können, hab ich ein Kit gebaut. Das folgende Bild wurde in Berlin gezeigt:
 
Siehe https://aidimme.sharepoint.com/:p:/r/sites/NARRATE/_layouts/15/Doc.aspx?sourcedoc=%7B7A54350C-A43E-4271-BCC6-E7F9B614DAA6%7D&file=WP-2%20First%20Half.pptx&action=edit&mobileredirect=true
Da hätte ich das Kit auch gerne vorgeführt, aber telegraf ging nicht. Das Kit ist weiter unten unter dem Namen ‚Monsterkit‘ beschrieben, weil es das größere ist und insbesondere viele Komponenten umfasst (MQTT, CEP, TimeseriesDB, KI).
Zur Beschreibung des Kits siehe 2.1 Das Monsterkit.
Inzwischen zeichnet sich ab, dass es in NARRATE sehr wenig um Sensoren und MQTT gehen wird und auch eine Timeseries-DB wohl nicht gebraucht wird. Alle Daten sollen mit zu dem Netz aus Digital Twins. Bei dieser Art der Speicherung rechnet man wohl nicht mit allzu vielen Events. Was jedoch auf jeden Fall mit dabei sein wird ist eine CEP und zwar Perseo. Das ist ein Esper mit einer Zusatzfunktion, das im FIWARE-Stack enthalten ist. Am besten Bescheid weiß hier Jorge von NUNSYS.


### 1.3	Task 4.1 – Resilience, Sustainability & Circularity Stress Testing Scenarios
Hier habe ich tatsächlich nichts getan, als Folien für das Review am 27.09.25 zu erstellen, die dann freundlicherweise von Andreas vorgetragen wurden. Vom Gesamtvortrag zu WP4 siehe die Folien zu Task 4.1. Folien 4 bis 9.
https://aidimme.sharepoint.com/:p:/r/sites/NARRATE/_layouts/15/Doc.aspx?sourcedoc=%7B21870B1A-388E-4087-ACB5-D24A0F5B3374%7D&file=12%20WP4-Narrate%20Technical%20Review%20Meeting%20M9-V2.pptx&action=edit&mobileredirect=true
Zur Vorbereitung der Folien habe ich ein Java-Programm geschrieben, das JUnit Tests enthält, mit denen Bedingungen getestet werden können, siehe 2.2 Das Esper Testkit. Diese Bedingungen sind bislang nur Esper Regeln (später Perseo), aber, wie dieses Netz von Digital Twins aussieht ist noch unklar (RDF Dokumente in SparQL?) und daher kann ich dagegen noch nix fragen.  

### 1.4	Task 7.3 – Innovation Potential & Impact
Auch hier gibt es nicht mehr als die Review-Folien, siehe
https://aidimme.sharepoint.com/:p:/r/sites/NARRATE/_layouts/15/Doc.aspx?sourcedoc=%7BFDBCC5E5-40B7-4EDB-A5DA-23D7F88A9FCA%7D&file=14%20WP7%20(F6S%2C%20SAG)%20Narrate%20presentation%20RM%2C%2027.09.2024.pptx&action=edit&mobileredirect=true
Folien 32 bis 36. Hiervon ist Folie 34 nicht völlig generisch, sondern gibt eine Vermutung ab, wo Innovationspotential in NARRATE liegen könnte. Folie 36 kündigt den Fragebogen an, den ich bisher noch nicht verfasst habe (versteh nicht alles darin). Als ich darüber gesprochen habe, hat Thomas Knothe angemerkt, den Unterschied zwischen state-of-the-art und der Innovation sollte auch erfragt werden. Dem habe ich zugestimmt und versprochen, dass solche Fragen auch vorkommen werden.

## 2	Die Kits

###2.1	Das Monsterkit
 
#### 2.1.1	Der MQTT Broker

In der Mitte sitzt ein MQTT Broker nämlich Apache Mosquitto, den ich als Docker-Container von  https://hub.docker.com/_/eclipse-mosquitto  runtergeladen habe (Version 2.0.18). Der Container wird gestartet mit „docker run -it -p 1883:1883 -p 9001:9001 -v C:/mosquitto/config/mosquitto.conf:/mosquitto/config/mosquitto.conf eclipse-mosquitto“. Ein geeignetes mosquitto.conf befindet sich unter /Mosquitto.

 

#### 2.1.2	Das Java GUI

In den Broker kommen MQTT Messages aus einem Java Programm. Da ich keine Sensoren habe, benutze ich ein kleines Java GUI um MQTT Messages per Hand zu erzeugen (Java-Projekt: narrateSensors). 
Wenn der MQTT Broker gestartet ist kann sich das GUI per ‚Connect‘ auf den Broker connecten (man sieht das auch auf der Konsole des Brokers) und nach einmal ‚Subscribe‘ sieht man immer alle auf den fünf Kanälen (Topics) erscheinenden MQTT Messages.
Man erzeugt eine MQTT Message, indem man ein Topic, eine Activity und einen Supplier auswählt, eine OrderID eingibt und ‚Publish‘ drückt. Eine Activity ist jeweils ein Punkt, den eine Bestellung (order) während ihrer Abwicklung durchläuft.

#### 2.1.3	Externe Events

Dieses GUI liest zusätzlich externe Informationen und schickt sie auch als MQTT Messages zum Broker. 
Die externen Informationen stammen aus drei Quellen:
1.	Reuters NewsAPI (war damals kostenlos, inzwischen kostet es was, d.h. es liefert nix mehr)
2.	TomTom (Verkehr) 
3.	Deutscher Wetterdienst (ist halt nur Deutschland, besser wäre Openweather)
Das Lesen der externen Events erfolgt im Java-Projekt narrateNews. Dieses wird mit gestartet, wenn narrateSensors los läuft. narrateNews dient zwei Zwecken. 
Zum einen werden Alarme erzeugt und als Events auf den MQTT-Broker geschickt, falls aus einem Kanal etwas erhalten wird, das Alarm-würdig scheint. Diese Alarme sieht man dann auch im GUI unter „mqtt/alarm“.
Zum zweiten, wird zu einem Event mit activityID = 0 (ordered) ein 5-Tupel mit externen Informationen hinzugefügt. Dies sind
•	Eine Information zum Tempo, das in der nächsten Straße gefahren wird (aus TomTom)
•	Drei Daten vom lokalen Wetter (Temperatur, Gesamtzustand, ev. Desaster)
•	Eine Information, ob etwas Desaströses von Reuters gemeldet wurde
 

Der Sinn des 5-Tuples ist, dass später die KI eine halbwegs sinnvolle Frage stellen kann. Das Modell wird mit Daten aus vorausgegangenen Lieferungen trainiert, nämlich einem 6-Tupel aus den fünf externen Parametern beim Ordern plus der Gesamtdauer der Lieferung. Das Modell kann man dann fragen, wie lange eine jetzt georderte Lieferung wohl brauchen wird, wenn man die fünf momentanen externen Parameter kennt.               
Das Einlesen von Reuters funktioniert so, dass man alle Headlines auf alarmierende Stichwörter überprüft. Diese Methode ist sehr simpel. Fraunhofer (Berlin) hat da was Klügeres. Sie haben Werkstudenten Headlines auf Relevanz beurteilen lassen (relevant/nicht relevant) und eine KI mit den Ergebnissen trainiert, die jetzt Headlines als relevant erkennt. Technisch benutzten sie RSS-Feeds. Der Ansprechpartner bei Fraunhofer ist Jan Torka.  

#### 2.1.4	InfluxDB / telegraf

Alle Events, interne, externe und später auch die aus der CEP werden in einer InfluxDB gespeichert. Dies ist der einzige Teil des Kits, der nicht lokal sondern auf dem Hetzner läuft, siehe https://narrate.sagresearch.de/signin, Credentials: jhb/Tantenwandern23&.
Damit die Events in die InfluxDB gelangen können, braucht man einen telegraf. Dieser kann erst nach dem MQTT-Broker gestartet werden. Für den Zugriff auf InfluxDB benötigt der telegraf einen API-Key, der ev. ausläuft. Ein geeignetes telegraf.conf befindet sich unter /telegraf. telegraf lauscht auf dem MQTT Broker, siehe hierzu den Abschnitt unter „# # Read metrics from MQTT topic(s)“. Die Events werden nach InfluxDB geschrieben, siehe hierzu den Abschnitt unter „# # Configuration for sending metrics to InfluxDB 2.0“.

 

#### 2.1.5	CEP

Auf dem MQTT Broker lauscht auch eine Esper CEP (in Java). Zuerst hatte ich hier APAMA, aber das wollte irgendwann eine Lizenz (als ich es außerhalb vom Designer starten wollte) und von da an hatte ich Esper (open-source, läuft in Java). Das Java-Programm heißt narrateEsper. Die CEP liest alle Events, die auf das Topic ‚mqtt/sensors‘ gehen und checkt, ob zwischen zwei aufeinanderfolgenden Punkten der Lieferkette nicht zu viel Zeit verstreicht. Wieviel Zeit verstreichen darf, hängt von den Punkten ab. Zwischen ‚loaded‘ und ‚arrived‘ dürfen 10 Sekunden verstreichen. Für alle anderen Zwischenräume gilt 5 Sekunden.

#### 2.1.6	Der Filler

Mit dem GUI genug Events zu verursachen, um danach mit dem KI Modell was anfangen zu können, wäre zu mühselig, daher gibt es ein Java-Programm narrateFiller, dass Events erzeugt und über MQTT in die Datenbank schreibt. Das Programm läuft etwa 25 Minuten und hat dann 10 Liefervorgänge erzeugt.

#### 2.1.7	Python KI

Die Python KI liest die 6-Tupel (5 externe Parameter + gesamte Lieferdauer) aus der Datenbank. Diese vereinfache ich noch: „serious“, also, ob extrem böses Wetter ist, vergesse ich, der fünfte bedeutet 1=clouds, 0=keine clouds, der sechste 1=Krieg, 0=kein Krieg. Damit wird das Modell trainiert und um eine Vorhersage bei angegebenen Parametern gebeten. Die Vorhersage ist, die Lieferung dauert 27 Sekunden. Ändert man die letzte Eingabe von „predict“ zu 0 (doch kein Krieg), geht es voraussichtlich schneller, nämlich in 21 Sekunden. 

Die vier benötigten Python-Sourcen sind narrateMain.py (siehe oben), narrateSix.py stellt die Tupel zusammen (u.a. ein Join über orderID), narrateInflux.py enthält den DB-Zugriff. Die Query darin enthält Parameter (momentan -6 und 2), die bestimmen über welchen Zeitraum Events gelesen werden. narrateEncode.py erzeugt die beiden 0/1 Parameter (den vierten und fünften).

### 2.2	Das Esper Testkit
Für die Task 4.1 ist vorgesehen, das in WP3 zu erstellende Netz von Digital Twins zu testen. Als Vorarbeit hierzu gibt es ein Kit, das JUnit Tests enthält, die Esper Regeln testen, nämlich esperTest.
Da MQTT im Projekt doch eher keine Rolle spielt und viel Aufwand darstellt (Rancher hoch für Docker, Mosquitto Docker Container), enthält das Esper Testkit kein MQTT mehr sondern nur noch Esper.
Zum Testen startet man /test/ItalyTest.java als JUnit Test. 

 
Startet man hingegen /main/Main öffnet sich ein GUI, das im oberen Drittel alle in /main/Queries.java gespeicherten Esper-Queries zeigt und in den anderen beiden Teilen das Erzeugen internen bzw. externer Events erlaubt.
 

Die sechste Query lautet: 
select a.*,b.location from pattern [every   a=SupplyChainEvent(activityID=2 and supplierID=2
-> b=TrafficEvent(location=\"Italy\”)
   and not timer:interval(4 seconds)]
Diese Query feuert, wenn ein internes Event mit (activityID = 2 und supplierID = 2) auftritt und binnen 4 Sekunden win Verkehrsproblem in Italien gemeldet wird (externes Event). Im folgenden Screen wurde je ein entsprechendes internes und ein externes Event ausgelöst. Die korrekte Warnung bzgl. des italienischen Verkehrs wird angezeigt. Außerdem die Warnung, dass das “loaded” Event nicht binnen zehn Sekunden von einem “arrived” Event gefolgt wurde, siehe Regel vier:
select a.* from pattern [every a=SupplyChainEvent(activityID=2 
-> (timer:interval(10 sec) 
    and not SupplyChainEvent(supplierID=a.supplierID and orderID=a.orderID
                         and activityID=3))]
 

Ich hatte geplant, dass man im oberen Teil, statt die vorhandenen Queries nur anzuzeigen, Queries neu anlegen, ändern und löschen kann, aber dazu bin ich nicht mehr gekommen. Zum Testen muss man jetzt main/Queries.java ändern und ev. in CEP/Initializer.java neue Queries bekannt machen, d.h. deployen und einen Listener registrieren.
