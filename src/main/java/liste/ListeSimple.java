package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne le nombre d'éléments actuellement présents dans la liste.
     * @return le nombre d'éléments de la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nouvel élément en tête de liste.
     * @param element valeur à insérer en tête
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la première occurrence d'un élément donné par une nouvelle valeur.
     * Ne fait rien si l'élément n'est pas trouvé.
     * @param element valeur recherchée (première occurrence)
     * @param nouvelleValeur valeur de remplacement pour cette occurrence
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie toutes les occurrences d'un élément donné par une nouvelle valeur.
     * @param element valeur recherchée (toutes les occurrences)
     * @param nouvelleValeur valeur de remplacement pour chaque occurrence
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Produit une représentation textuelle de la liste.
     * @return la chaîne représentant la liste au format "ListeSimple(Noeud(x), ...)"
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence d'un élément donné dans la liste.
     * Ne fait rien si l'élément n'existe pas.
     * @param element valeur à supprimer (première occurrence)
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences d'un élément donné dans la liste (version publique).
     * @param element valeur à supprimer (toutes les occurrences)
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime récursivement toutes les occurrences d'un élément à partir d'une tête donnée.
     * @param element valeur à supprimer (toutes les occurrences)
     * @param tete tête de la sous-liste à traiter
     * @return la nouvelle tête de la sous-liste après suppression
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Renvoie l'avant-dernier nœud de la liste.
     * @return l'avant-dernier nœud, ou {@code null} si la liste a moins de deux éléments
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des nœuds de la liste en place.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Renvoie le nœud précédent d'un nœud donné dans la liste.
     * Précondition : {@code r} appartient à la liste et n'est pas {@code null}.
     * @param r nœud dont on cherche le précédent
     * @return le nœud précédent de {@code r}
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange en place deux nœuds existants de la liste (réagence les liens sans créer de nœuds).
     * Si {@code r1 == r2}, la méthode ne fait rien.
     * @param r1 premier nœud à échanger
     * @param r2 second nœud à échanger
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2) {
            return;
        }

        if (r1 != tete && r2 != tete) {
            Noeud precedentR1 = getPrecedent(r1);
            Noeud precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            Noeud precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else { 
            Noeud precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }

        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }
}
