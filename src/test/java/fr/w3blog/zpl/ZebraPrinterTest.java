package fr.w3blog.zpl;

import java.io.IOException;

import fr.w3blog.zpl.model.element.ZebraBarCode128;
import fr.w3blog.zpl.utils.ZebraUtils;
import junit.framework.TestCase;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.ZebraPrintException;
import fr.w3blog.zpl.model.element.ZebraBarCode39;
import fr.w3blog.zpl.model.element.ZebraText;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;

/**
 * These test will print example on printer 300dpi
 * 
 * @author ttropard
 * 
 */
public class ZebraPrinterTest extends TestCase {

	private String ip = "127.0.0.1";
	private int port = 9100;


	public void testPrint(){
//		ZplPrinter p = new ZplPrinter("ZDesigner 105SLPlus-300dpi ZPL");
        ZplPrinter p = new ZplPrinter("\\\\172.16.2.7\\ZDesigner 105SL 300DPI");

//       输出字符调试
		String lab = "" +
				"^XA\n" +
				"^JMA\n" +
				"^LL200\n" +
				"^PW680\n" +
				"^MD10\n" +
				"^PR2\n" +
				"^PON\n" +
				"^LRN\n" +
				"^LH0,0\n" +
				"^FO20,0\n" +
				"^A0N,72,72\n" +
				"^FD Hello World!^FS\n" +
				"^XZ";

		//条码模板
		String lab2="^XA\n" +
				"^MMT\n" +
				"^PW1000\n" +
				"^LL100\n" +
				"^FT100,5\n" +
				"^BY2.5,2,50\n" +
				"^BCN,60,Y,N,N\n" +
				"^FD" +
				"1AASD023561" +
				"^FS\n" +
				"^XZ";
//        String bar0Zpl = "^FO200,0^BY3,2.0,50^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
//        p.setBarcode(bar, bar0Zpl);

//		String zpl = p.getZpl(lab2);

		boolean result1 = p.print(lab2);//打印
	}
	/**
	 * 打印条码
	 * 
	 * @throws IOException
	 * @throws ZebraPrintException
	 */
	public void testZebraLibrary() throws IOException, ZebraPrintException
	{
		ZebraLabel zebraLabel = new ZebraLabel(1000, 100);
		zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);

//		zebraLabel.addElement(new ZebraText(10, 84, "Product:", 14));
//		zebraLabel.addElement(new ZebraText(395, 85, "Camera", 14));

		//打印文字
//		zebraLabel.addElement(new ZebraText(100, 80, "CA201212AA", 14));

//		ZebraBarCode39 barcode = new ZebraBarCode39(50, 10, "CA201212AA", 60, 2, 1);
		ZebraBarCode128 barcode = new ZebraBarCode128(100, 5, "AASD023561", 50, true, 3, 1);

		zebraLabel.addElement(barcode);


		ZebraUtils.printZpl(zebraLabel,"\\\\172.16.2.7\\ZDesigner 105SL 300DPI");
//		ZebraUtils.printZpl(zebraLabel,"ZDesigner 105SLPlus-300dpi ZPL");
	}

	/**
	 * Test with many instructions (based on real case)
	 *
	 * @throws IOException
	 * @throws ZebraPrintException
	 */
	public void testZebraLibrary1() throws IOException, ZebraPrintException
	{
		ZebraLabel zebraLabel = new ZebraLabel(912, 912);
		zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);

		zebraLabel.addElement(new ZebraText(10, 84, "Product:", 14));
		zebraLabel.addElement(new ZebraText(395, 85, "Camera", 14));

		zebraLabel.addElement(new ZebraText(10, 161, "CA201212AA", 14));

		zebraLabel.addElement(new ZebraBarCode39(10, 297, "CA201212AA", 118, 2, 2));

		zebraLabel.addElement(new ZebraText(10, 365, "Qté:", 11));
		zebraLabel.addElement(new ZebraText(180, 365, "3", 11));
		zebraLabel.addElement(new ZebraText(317, 365, "QA", 11));

		zebraLabel.addElement(new ZebraText(10, 520, "Ref log:", 11));
		zebraLabel.addElement(new ZebraText(180, 520, "0035", 11));
		zebraLabel.addElement(new ZebraText(10, 596, "Ref client:", 11));
		zebraLabel.addElement(new ZebraText(180, 599, "1234", 11));


//		ZebraUtils.printZpl(zebraLabel,"\\\\172.16.2.7\\ZDesigner 105SL 300DPI");
		ZebraUtils.printZpl(zebraLabel,"ZDesigner 105SLPlus-300dpi ZPL");
	}

}
