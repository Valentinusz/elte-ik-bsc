class Id {
    // static = osztály szintű metódus/változó/osztály
    private int id;
    private static int id_count;

    public Id () {
        this.id = ++id_count;
    }
}
