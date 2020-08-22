package pojo;

import java.awt.datatransfer.*;
import java.io.IOException;

public class DataSelection implements Transferable, ClipboardOwner {

    public static final DataFlavor rangeFlavor = new DataFlavor(Object.class, "Report Range");//class为自定义的java类 字串随便
    private static final DataFlavor[] flavors = { rangeFlavor };
    private Object data;

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return (DataFlavor[]) flavors.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (int i = 0; i < flavors.length; i++) {
            if (flavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(flavors[0])) {
            return data;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }

}
