# BAMK
Bamk är ett 2d shooter spel som fokuserar på simpla spel mekaniker som gör spelet väldigt levande. Skott kan studsa och beroende på hur man rör sig kommer skottet påverkas. Allt detta kan användas för att förvånna motståndaren. Man spelar på varsin halva när man är 2 spelare. Man kan röra sig inom halvan men ej på den andra halvan. Skotten skjuts endast åt en riktning men man kan manipulera detta genom att röra sig när man skjuter. Spelet ser väldigt simpelt ut från början, men utnyttjar man fysiken av det så finns det väldigt många komplexa sätt att vinna.

## Funktionella krav
- 1 spelar läge ✔️
- 2 spelar ska kunna spela spelet lokalt ✔️
- Accelerations och hastighets funktioner samt friktion ✔️
- Det ska finnas minst 2 olika "karaktärer" som man ska kunna spela som har olika egenskaper ✔️
- Meny, helst med noder istället för canvas ✔️
	


## Icke-funktionella krav
- Spelet ska vara väldigt intutivt ✔️
- Java ska användas som programmeringsspråk ✔️
- Programmet ska ha en god objektorienterad design. Designen ska finnas dokumenterad, t.ex. i diagramform. ✔️
- Programmet ska vara så lätt att lära sig att en normalbegåvad labbassistent kan hantera det efter några minuters utbildning. ✔️

## Idéer till yttligare funktioner
- Mer karaktärer 🚧
- Programmet ska fungera I alla storlekar, till visst minsta storlek ✔️
- Spelet ska fungera bra I alla framerates, alltså ska det använda delta tiden istället för frames vid beräkning ✔️
- Simpel motståndare
- Sockets över LAN samt WAN med öppnade portar ✔️

## Objektorientering
- Hierarki ✔️
- Arv ✔️
- Interface och abstrakta klasser ✔️
- Inkapsling ✔️
- Komposition ✔️
	ex Gamecore använder EntityManager
- Enumeration ✔️
	ex Direction, KeySchema
- Polymorfism ✔️
	ex. Entity.update(), Gamecore.update()

![](https://i.imgur.com/7YLh9Gi.png)
