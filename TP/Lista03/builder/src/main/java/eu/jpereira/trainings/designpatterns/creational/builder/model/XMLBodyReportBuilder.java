package eu.jpereira.trainings.designpatterns.creational.builder.model;

import eu.jpereira.trainings.designpatterns.creational.builder.xml.XMLReportBody;

public class XMLBodyReportBuilder extends XMLReportBody implements ReportBodyBuilder {

    private XMLReportBody reportBody;

    public XMLBodyReportBuilder() {
        reportBody = new XMLReportBody();
    }

    @Override
    public ReportBodyBuilder setCustomerName(String customerName) {
        reportBody.putContent("<sale><customer><name>");
        reportBody.putContent(customerName);
        reportBody.putContent("</name>");

        return this;
    }

    @Override
    public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
        reportBody.putContent("<phone>");
        reportBody.putContent(phoneNumber);
        reportBody.putContent("</phone></customer>");

        return this;
    }

    @Override
    public ReportBodyBuilder withItems() {
        reportBody.putContent("<items>");

        return this;
    }

    @Override
    public ReportBodyBuilder newItem(String name, int quantity, double price) {
        reportBody.putContent("<item><name>");
        reportBody.putContent(name);
        reportBody.putContent("</name><quantity>");
        reportBody.putContent(quantity);
        reportBody.putContent("</quantity><price>");
        reportBody.putContent(price);
        reportBody.putContent("</price></item>");

        return this;
    }

    @Override
    public ReportBody getReportBody() {
        reportBody.putContent("</items></sale>");

        return reportBody;
    }
}
