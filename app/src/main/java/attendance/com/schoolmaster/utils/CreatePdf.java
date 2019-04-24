package attendance.com.schoolmaster.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import attendance.com.schoolmaster.model.AttendanceReportModel;

public class CreatePdf {

    private File file;

    public boolean createAttendancePdf(String dir, String filename , List<AttendanceReportModel> mStudentList)
    {
        boolean isSuccessful= true;
        Document document = new Document();
        PdfPCell cell;

        try {
            file = new File(dir, filename);
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(document, fOut);

            //open the document
            document.open();
            PdfPTable headerTable = new PdfPTable(2);
            cell  = getNormalCell("Attendance Report",18,Element.ALIGN_CENTER,Rectangle.NO_BORDER,Font.BOLD);
            cell.setColspan(2);
            headerTable.addCell(cell);
            headerTable.addCell(getNormalCell("Standard: 4th",12,Element.ALIGN_LEFT,Rectangle.NO_BORDER,Font.NORMAL));
            headerTable.addCell(getNormalCell("Division: A",12,Element.ALIGN_LEFT,Rectangle.NO_BORDER,Font.NORMAL));
            headerTable.addCell(getNormalCell("From Date: 01/01/2019",12,Element.ALIGN_LEFT,Rectangle.NO_BORDER,Font.NORMAL));
            headerTable.addCell(getNormalCell("To Date: 31/01/2019",12,Element.ALIGN_LEFT,Rectangle.NO_BORDER,Font.NORMAL));
            headerTable.addCell(getNormalCell("Total Students: 20",12,Element.ALIGN_LEFT,Rectangle.NO_BORDER,Font.NORMAL));
            headerTable.addCell(getNormalCell("Total Days: 23",12,Element.ALIGN_LEFT,Rectangle.NO_BORDER,Font.NORMAL));

            /**
             * Student Table
             */
            PdfPTable studentTable = new PdfPTable(4);
            studentTable.addCell(getNormalCell("Roll Number",12,Element.ALIGN_LEFT,Rectangle.BOX,Font.BOLD));
            studentTable.addCell(getNormalCell("Name",12,Element.ALIGN_LEFT,Rectangle.BOX,Font.BOLD));
            studentTable.addCell(getNormalCell("No of days Present",12,Element.ALIGN_LEFT,Rectangle.BOX,Font.BOLD));
            studentTable.addCell(getNormalCell("No of days Absent",12,Element.ALIGN_LEFT,Rectangle.BOX,Font.BOLD));

            for(AttendanceReportModel student:mStudentList)
            {
                studentTable.addCell(getNormalCell(student.getRollNumber(),12,Element.ALIGN_LEFT,Rectangle.BOX,Font.NORMAL));
                studentTable.addCell(getNormalCell(student.getStudentName(),12,Element.ALIGN_LEFT,Rectangle.BOX,Font.NORMAL));
                studentTable.addCell(getNormalCell(String.valueOf(student.getNoOfPresent()),12,Element.ALIGN_LEFT,Rectangle.BOX,Font.NORMAL));
                studentTable.addCell(getNormalCell(String.valueOf(student.getNoOfAbsents()),12,Element.ALIGN_LEFT,Rectangle.BOX,Font.NORMAL));
            }

            cell = new PdfPCell(studentTable);
            cell.setColspan(2);
            headerTable.addCell(cell);
            document.add(headerTable);
        }
        catch (Exception e) {
            isSuccessful = false;
            e.printStackTrace();
        } finally {
            document.close();
            return isSuccessful;
        }

    }


    private PdfPCell getNormalCell(String string, float size, int horizontalAlignment, int border, int style)

            throws DocumentException, IOException {

        if (string != null && "".equals(string)) {
            return new PdfPCell();
        }
        Font f = FontFactory.getFont(FontFactory.TIMES_ROMAN, size,style);
        f.setSize(size);
        PdfPCell cell = new PdfPCell(new Phrase(string, f));
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setBorder(border);
        return cell;

    }
}
