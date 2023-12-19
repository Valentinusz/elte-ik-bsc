public class AnnouncementBoard extends Thread {
    // TODO4: Készíts egy map-szerű adatszerkezetet, amely a vendégek neveit és az általuk rendelt ételt tartalmazza!

    public void announceReady(String customer, String food) {
        // TODO4: Tedd be az elkészült rendelés adatait az adatszerkezetbe!
    }

    @Override
    public void run() {
        // TODO4: Írd ki az összes elkészült rendelést másodpercenként, amíg az étterem nyitva van, és nem készült még el az összes rendelés!
        // TODO4: Az elejét így írd ki:
        // TODO4:     System.out.println("Elkészült rendelések: ");
        // TODO4: Az egyes rendeléseket pedig így:
        // TODO4:       System.out.println("    " + <vendég neve> + ": " + <étel>);
    }
}
