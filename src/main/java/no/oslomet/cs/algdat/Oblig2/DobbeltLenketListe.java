package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    //oppgave 1
    public DobbeltLenketListe() {
        hode = null;
        antall = 0;
        hale = null;
        endringer = 0;
    }

    //oppgave 1
    public DobbeltLenketListe(T[] a) {
        this.hode = null;
        this.hale = null;

        int i = 0;
        for (; i < a.length && a[i] == null; i++) ;

        if (i < a.length) {
            Node<T> p = hode = new Node<>(a[i], null, null);
            antall = 1;

            for (i++; i < a.length; i++) {
                if (a[i] != null) {
                    Node<T> q = new Node<>(a[i], p, null);
                    p.neste = q;
                    p = q;
                    antall++;
                }
            }
            hale = p;
        }

    }

    //oppgave 1
    @Override
    public int antall() {
        return antall;                               // returnerer antallet
    }

    // oppgave 1
    @Override
    public boolean tom() {
        return (antall == 0 && hode == null && hale == null);           // listen er tom hvis antall er 0
    }

    // oppgave 2
    @Override
    public String toString() {
        if (antall == 0) return "[]";           // hvis tabellen er tom returner to klammer

        StringBuilder s = new StringBuilder();
        Node<T> peker = hode;                  // Node som peker til første node
        s.append('[');

        while (peker != null && peker != hale) {
            s.append(peker.verdi + ", ");
            peker = peker.neste;
        }


        s.append(peker.verdi + "]");            // avslutter med å legge til siste verdi og avsluttende klamme
        return s.toString();
    }

    // oppgave 2
    public String omvendtString() {
        StringBuilder ut = new StringBuilder("[");

        Node<T> p = hale;      // Setter node p til hale
        while (p != null) {        // Hvis p er både hale og hode så finnes det kun et element i array
            if (p == hode) {
                ut.append(p.verdi);
            } else {
                ut.append(p.verdi).append(", ");
            }
            p = p.forrige;             // Går fra hale til hode med å sjekke elementet som var før det siste.
        }
        ut.append("]");

        return ut.toString();       // Returnerer toString
    }

    //oppgave 2
    @Override
    public boolean leggInn(T verdi) {
        Node<T> p = new Node<>(verdi);
        if (verdi == null) {
            throw new NullPointerException("Verdien kan ikke være null");
        }

        if ((hode == null) && (hale == null)) {      //Hvis listen er tom
            hode = p;
            hale = p;
            antall++;
            endringer++;
        } else {      //Dersom det finnes en eller flere noder i listen fra før
            Node q = hale;
            hale = p;
            p.forrige = q;
            q.neste = p;
            antall++;
            endringer++;
        }

        return true;
    }

    // Hjelpemetode 1
    private Node<T> finnNode(int indeks) {

        Node<T> p = null;

        if (indeks < antall / 2) {        //sjekker om indeksen er nærmere hode eller hale
            // hvis indeksen er nærmerer til hode
            p = hode;                       // p blir til hode før for-løkke
            for (int i = 0; i < indeks; i++) {  // for-løkke starter fra hode og går gjennom listen
                p = p.neste;
            }
        } else // hvis indeksen er nærmerer til halen.
        {
            p = hale; // p blir halen før for-løkke
            for (int i = antall - 1; i > indeks; i--) { // for-løkke starter fra halen og går gjennom listen
                p = p.forrige;
            }
        }
        return p;   // returnerer node fra ønsket indeks
    }

    //hjelpemetode 2
    private static void fratilKontroll(int antall, int fra, int til) {

        if (fra < 0) {          // fra er negativ
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        } else if (antall < til) {       // til er utenfor tabellen
            throw new IndexOutOfBoundsException("til(" + til + ") > antall(" + antall + ")");
        } else if (til < fra) {       // fra er større enn til
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }
    }

    // oppgave 3
    // Kildekode er det samme som i kompendiet: Avsnitt 3.3.3: klassens øvrige metoder
    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    //oppgave 3a
    // Kildekode er det samme som i kompendiet: Avsnitt 3.3.3: klassens øvrige metoder
    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false); // false: indeks = antall er ulovelig

        if (nyverdi == null) throw new NullPointerException("Ikke tillatt med null som verdi");
        Node<T> n = finnNode(indeks);
        T gammelVerdi = n.verdi;
        n.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }

    //oppgave 3b
    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

        Node<T> r = finnNode(fra);    //Går inn i hode i listen, med finnNode, med indeks = fra.

        for (int i = 0; i < (til - fra); i++) {     //Legger til verdiene fra nodene i listen inn i sublisten, helt til "til"
            subliste.leggInn((T) r.verdi);
            r = r.neste;
        }

        return subliste;
    }

    //oppgave 4
    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }


    //oppgave 4
    @Override
    public int indeksTil(T verdi) {
        //Kilde kode fra kompediet: Avsnitt 3.2.2: Programkode 3.2.2 g)
        if (verdi == null) return -1; //Hvis verdi er lik 0 returner -1
        Node<T> p = hode; //Setter p til hode
        for (int i = 0; i < antall; i++, p = p.neste) { //Går gjennom antall noder og setter p til neste
            if (p.verdi.equals(verdi)) { //Hvis hode(p) er lik verdien til noden, returneres indeksen
                return i;
            }
        }
        return -1;
    }

    // oppgave 5
    @Override
    //Kilde kode fra kompediet: Kapittel 3: Dobbelt lenket liste Programkode 3.3.2 g)
    public void leggInn(int indeks, T verdi) {
        nullSjekk(verdi);

        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks " +
                    indeks + " er negativ!");
        } else if (indeks > antall) {
            throw new IndexOutOfBoundsException("Indeks " +
                    indeks + " > antall(" + antall + ") noder!");
        } else if (antall == 0)  // tom liste
        {
            hode = hale = new Node<>(verdi, null, null);
        } else if (indeks == 0)  // ny verdi forrest
        {
            hode = hode.forrige = new Node<>(verdi, null, hode);
        } else if (indeks == antall)  // ny verdi bakerst
        {
            hale = hale.neste = new Node<>(verdi, hale, null);
        } else {
            Node<T> p = finnNode(indeks);  // ny verdi til venstre for p
            p.forrige = p.forrige.neste = new Node<>(verdi, p.forrige, p);
        }

        antall++;      // ny verdi i listen
        endringer++;   // en endring i listen

    }

    //Kilde kode fra kompediet: Avsnitt 3.2.1: hjelpemetode
    private static <T> void nullSjekk(T verdi) {
        if (verdi == null) throw
                new NullPointerException("Ikke tillatt med null-verdier!");
    }

    //oppgave 6
    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {    //sjekker om verdi er null
            return false;
        }

        Node<T> q = hode;    //hjelpe-pekere

        while (q != null) {   //bruke q til å finne verdien T

            if (q.verdi.equals(verdi)) {
                break;
            }
            q = q.neste;
        }
        if (q == null) {
            return false;
        }
        else if(antall == 1) {
            hode = null;
            hale = null;
        }

        else if (q == hode) {
            hode = hode.neste;
            hode.forrige = null;
        }
        else if (q == hale) {     // om q er siste element, setter halen før q
            hale = hale.forrige;
            hale.neste = null;
        } else {
            q.forrige.neste = q.neste;
            q.neste.forrige = q.forrige;
        }

        q.verdi = null;    //nuller q sine pekere
        q.neste = null;
        q.forrige = null;

        antall--;          // Antall reduseres, endringer oppdateres, og p verdi blir returnert
        endringer++;

        return true;
    }

    //oppgave 6
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);

        Node<T> p = hode;

        if (antall == 1) {   // er antallet lik 1, er p hode og hode,halle er null
            hode = null;
            hale = null;

        }
        else if (indeks == 0) {   // Første noden fjernes ved å sette hode sin forrige på "null" og oppdatere hode til å bli hode sin neste.
            hode = hode.neste;
            hode.forrige = null;

        } else if (indeks == antall-1) {  // Siste noden fjernes ved å sette hale sin neste på "null"
            p = hale;                               // og oppdatere hale til å bli hale sin forrige, altså siste elementet.
            hale = hale.forrige;
            hale.neste = null;

        } else {
            p = finnNode(indeks);// fjerner en node og bruker for-løkke for å oppdatere node q
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }
        T nodeVerdi = p.verdi;
        p.verdi = null;
        p.forrige = null;
        p.neste = null;

        antall--;              // Antall reduseres, endringer oppdateres, og p verdi blir returnert
        endringer++;
        return nodeVerdi;
    }

    @Override
    // kildekode husk kilde
    public void nullstill() {
        Node<T> p = hode;

        while (p != null) {
            Node<T> q = p.neste;

            p.neste = null;     // for resirkulering
            p.forrige = null;   // for resirkulering
            p.verdi = null;     // for resirkulering

            p = q;
        }

        antall = 0;
        hode = hale = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new  DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            //Kilde kode fra kompediet: Avsnitt 3.3.4: Programkode 3.3.4 c)-------
            indeksKontroll(indeks);
            hode = finnNode(indeks);
            fjernOK = false;    // blir sann når next() kalles
            iteratorendringer = endringer;    // teller endringer
        }

        //Kilde kode fra kompediet: Avsnitt 3.2.1: hjelpemetode
        private void indeksKontroll(int indeks) {
            if (indeks < 0) {
                throw new IndexOutOfBoundsException("Indeks " +
                        indeks + " er negativ!");
            } else if (indeks >= antall) {
                throw new IndexOutOfBoundsException("Indeks " +
                        indeks + " >= antall(" + antall + ") noder!");
            }
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            //Kilde kode fra kompediet: Avsnitt 3.3.4: Programkode 3.3.4 c)
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen har blitt endret!");
            }
            if (hode == null) {
                throw new NoSuchElementException("Ingen flere verdier i listen!");
            }
            fjernOK = true;
            T verdi = denne.verdi;       // tar vare på verdien i hode
            denne = denne.neste;          // flytter hode til neste
            return verdi;
        }

        @Override
        public void remove() {
            //Kilde kode fra kompediet: Avsnitt 3.3.4: Programkode 3.3.4 d)
            if (!fjernOK) {
                throw new IllegalStateException("Kan ikke fjerne en verdi nå!");
            }
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen har blitt endret!");
            }
            fjernOK = false;
            Node<T> q;

            if (denne == null) { // den siste skal fjernes
                q = hale;
            }
            else {
                q = denne.forrige;
            }
            if (q == hode) { // den første skal fjernes
                if (antall == 1) { // bare en node i listen
                    hode = hale = null;
                }
                else {
                    hode = hode.neste;
                    hode.forrige = null;
                }
            }
            else if(q == hale) {
                hale = hale.forrige;
                hale.neste = null;
            }
            else {
                q.forrige.neste = q.neste;
                q.neste.forrige = q.forrige;
            }
            antall--;             // en node mindre i listen
            endringer++;          // en endring i listen
            iteratorendringer++;  // en endring i iteratoren
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


