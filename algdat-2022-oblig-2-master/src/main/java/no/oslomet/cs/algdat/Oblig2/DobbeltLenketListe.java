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

    public DobbeltLenketListe() {
    }

    public DobbeltLenketListe(T[] a) {
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        //Kilde kode fra kompediet: Avsnitt 3.2.2: Programkode 3.2.2 g)
        return indeksTil(verdi) != -1; //Returnerer true hvis listen inneholder verdi og false ellers
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

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

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        //Kilde kode fra kompediet: Avsnitt 3.3.4: Programkode 3.3.4 e)
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        //Kilde kode fra kompediet: Avsnitt 3.3.4: Programkode 3.3.4 e)---
        indeksKontroll(indeks);
        return new DobbeltLenketListeIterator(indeks);
    }

    private void indeksKontroll(int indeks) {
        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks " +
                    indeks + " er negativ!");
        }
        else if (indeks >= antall) {
            throw new IndexOutOfBoundsException("Indeks " + indeks + " >= antall(" + antall + ") noder!");
        }
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // hode starter på den første i listen
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
        //Kilde kode fra kompediet: Avsnitt 3.3.3: Programkode 3.3.3 a)
        private Node<T> finnNode(int indeks) {
            Node<T> p = hode;
            for (int i = 0; i < indeks; i++) p = p.neste;
            return p;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            //Kilde kode fra kompediet: Avsnitt 3.3.4: Programkode 3.3.4 c)
            if (hode == null) {
                throw new NoSuchElementException("Ingen flere verdier i listen!");
            }
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen har blitt endret!");
            }
            fjernOK = true;
            T verdi = hode.verdi;       // tar vare på verdien i hode
            hode = hode.neste;          // flytter hode til neste
            return verdi;
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