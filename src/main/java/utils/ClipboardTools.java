package utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClipboardTools {
    private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public static void copy(List<Vector> copyData) {
        String result = copyData.stream()
            .map(row -> {
                Stream<Object> stream = row.stream();
                return stream.map(s -> s == null ? "" : s.toString())
                        .reduce("", (r, s) -> r + "\t" + s)
                        .replaceFirst("\t", "");
            }).reduce("", (r, s) -> r + "\n" + s)
            .replaceFirst("\n", "");
        //构建String数据类型
        StringSelection selection = new StringSelection(result);
        clipboard.setContents(selection, null);
    }

    public static List<Vector> paste() {
        Transferable content = clipboard.getContents(null);//从系统剪切板中获取数据
        String text = null;//从数据中获取文本值
        if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {//判断是否为文本类型
            try {
                text = (String) content.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception e) {
                // TODO
                e.printStackTrace();
            }
        }
        if (text == null) {
            return null;
        }
        return Stream.of(text.split("\n"))
                .map(s -> new Vector(Arrays.asList(s.split("\t"))))
                .collect(Collectors.toList());
    }

}
