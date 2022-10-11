package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;


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
        int i =0;
        for (; i < a.length && a[i] == null; i++);

        if (i<a.length){
            Node<T> p = hode = new Node <> (a[i], null, null);
            antall =1;

            for (i++; i <a.length; i++){
                if (a[i] != null){
                    Node <T> q = new Node <>(a[i],p,null);
                    p.neste =q;
                    p=q;
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
        throw new UnsupportedOperationException();
    }
    // oppgave 2
    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    //oppgave 2
    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
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
        }

        else if(antall < til) {       // til er utenfor tabellen
            throw new IndexOutOfBoundsException("til(" + til + ") > antall(" + antall + ")");
        }
        else if (til < fra) {       // fra er større enn til
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }
    }
    // oppgave 3
    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks, false);

        Node<T> s = finnNode(indeks);
        return s.verdi;
    }

    //oppgave 3a
    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false); // false: indeks = antall er ulovelig

        if (nyverdi == null) throw new NullPointerException("Ikke tillatt med null som verdi");
        Node<T> n = finnNode(indeks);
        T gammelVerdi = n.verdi;
        n.verdi = nyverdi;
        endringer ++;
        return gammelVerdi;
    }

    //oppgave 3b
    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

        Node <T> r = finnNode(fra);    //Går inn i hode i listen, med finnNode, med indeks = fra.

        for (int i = 0 ; i < (til - fra) ; i++){     //Legger til verdiene fra nodene i listen inn i sublisten, helt til "til"
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
        //Kilde kode fra kompediet: Kapittel 3: Dobbelt lenket liste
        if (verdi == null) {
            return -1;
        }
        Node<T> p = hode;
        for (int i = 0; i < antall; i++, p = p.neste) {
            if (p.verdi.equals(verdi)) {
                return i;
            }
        }
        return -1;

    }

    // oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }


    //oppgave 6
    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    //oppgave 6
    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


