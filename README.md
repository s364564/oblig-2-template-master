
# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer
*Problemer med access for andre collaborate som gjøre vi ikke kunne commite og pushe endringene til github. 
*fiksa det på torsdag i øvingstimen, og fiksa gradle også. 
*på grunn av, er mange av commitene lagt til forsent i github. 
Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Nasima Josefi, S354388, s354388@oslomet.no
* David Thoresen, S364564, s364564@oslomet.no
* Arber Adrian Anadolli, s364553, 364553@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Nasima har hatt hovedansvar for oppgave 1, 3, 6 . 
* David har hatt hovedansvar for oppgave 4, 8 og 9.
* Arber har hatt hovedansvar for oppgave 2, 5 og 7 . 
* vi kjøper med oppgave 10 sammen 

# Oppgavebeskrivelse

I oppgave ble først antall variabel returnert i antall metoden og boolean metoden ble brukt for å sjekke om listen er tom.
Lager så konstruktøren for dobbeltlenketliste, som skal opprette en dobbeltlenketliste med verdier fra tabellen a. 
i tilegg vi variablen antall øke for hvert element som legges inn i listen fra tabellen. 

I oppgave 2 så gikk vi frem ved å lage en toString metode, metoden først sjekker om tabellen er tomt, og deretter danner en ny tegnstreng og legger en [ i tengstrengen.
Deretter vil metoden sjekke om peker != null eller hale, dersom peker har verdi vil den bli lagt inn i tegnstrengen. På slutten av metoden legger vi inn en ] for å avslutte tegnstrengen.
Vi også lagde metoden omvendtString som settes i gang på samma måte som toString metoden. Men i denne metoden legger vi inn elementene motsatt vei, fra hale i stedet for hode.
Metoden leggInn starter med å sjekke om verdiene er null, dersom vi ikke har null verdier sjekker vi om listen er tomt på forhånd. Metoden legger inn verdiene bakerst, og teller antall og endringer. 


I oppgave 3 lagde vi hjelpemetoden "finnNode", som skal finne og returnere en node med gitt indeks. 
for å løse oppgaven brukte vi indekskontroll metoden som sjekker om den gitte indeksen er gyldig.
Etter å ha funnet ut hvilken side av liste indeksen ligger, har vi iterert over listen ved hjelp av neste-og forrige pekere. 
Til slutt returnerte noden som ligger i den gitte posisjonen. 

metoden hent, henter verdien til en node med gitt indeks. her sjekket vi også om indeksen er gyldig. 
brukte finnNode metoden til å finne noden og den returnerte verdien til noden. 

i oppdatert metoden, sjekket vi igjen om indeksen er gyldig, og om gitt verdi er null eller ikke. 
dermed fant vi noden, og lagret verdien i en midlertidig node. oppdatert den gamle verdien med den nye verdien. 
og returnerte vi den lagrede verdien altså den gamle verdien. 

i metoden subliste sjekket vi om intervaller er gyldig eller ikke.
deretter instantierte vi en subliste. brukte finnNode metoden til å finne noden fra, og 
så itererte over intervallet ved bruk av neste pekeren, la noden i sublisten ved hjelp av legginn metoden. 
til slutt returnerte vi sublisten. 


I oppgave 4 så gikk vi frem ved å lage en if setning.
Hvis "verdi" er tom vil den returnere -1, ellers vil resten av koden kjøre.
Deretter går vi gjennom antall noder og setter p til "neste".
Hvis verdi av p er lik "verdi", så returnerer den indeksen.
I inneholder metoden så returnes true hvis listen inneholder verdi og false ellers.

I oppgave 5 så gikk vi frem ved å lage en if setning som sjekker om verdien metoden tar inn er gyldig.
Dersom vi får null, kastes det en feilmelding. Metoden tar inn 2 parametere, en indeks
og en verdi. Int indeks bestemmer indeksen verdien kommer til å legges inn i. Metoden bruker
if setninger for å først sjekke om indeks er 0, dersom indeks er 0, vil verdien legges inn først. Den andre 
if setningen sjekker om indeksen er antall, dersom indeks == antall vil verdien legges inn på slutten.
Og hvis indeks er ikke den første eller den siste verdien vil else setningen ta vare på resten. 

I oppgave 6 skulle vi fjerne en node fra en gitt indeks. første sjekket vi om indeksen er gydlig, og så gjernet vi det element fra listen. 
oppdaterte pekere før og etter det element som ble slett. 

I oppgave 7 så gikk vi frem ved å lage en metode som sjekker om hode har en verdi, dersom
hode har en verdi vil metoden fjerne verdier, p blir p.neste og metoden gjentar seg så langt det 
finnes verdier i p. 

I oppgave 8 så gikk vi frem ved å lage en if setning.
Hvis "hode" er tom vil det kastes et NoSuchElementException untak.
Hvis "iteratorendringer" ikke er lik "endringer" så vil det kastes ConcurrentModificationException untak.
Så tar vi vare på verdien i hode og flytter hode til neste.
Til slutt returneres verdi.

I oppgave 9 så gikk vi frem ved å lage en if setning.
Hvis "fjernOk" er false vil det kastes et NoSuchElementException untak.
Hvis "iteratorendringer" ikke er lik "endringer" så vil det kastes ConcurrentModificationException untak.
Hvis "antall" er lik 1, altså hvis det bare er en node, så settes hode og hale til null.
Ellers hvis "hode" er tom så fjernes den siste.
Ellers hvis forrige "hode" er lik hode, så fjernes den første.
Ellers så fjernes "q".
Til slutt legger vi på en endring i listen og iteratoren og fjerner en node.

i oppgave 10 tok vi først de to verdiene som vil sammenlige innenfor lokke.
etterpå skrev vi verdi 1 og verdi 2 i hent-metoden, for å inkludere dem i compare-metoden.
for oppdatere plasseringene brukte vi oppdater-metoden.
bruk av bubblesortering er nokså inneffktiv måte å sorter det på, kunne ha brukt f.eks. quicksort.
synes at bubblesorteringen var nok lettere å forstå og lettere å implimentere det her. 
