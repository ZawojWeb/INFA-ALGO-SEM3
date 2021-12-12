package eu.jpereira.trainings.designpatterns.creational.builder.model;

import eu.jpereira.trainings.designpatterns.creational.builder.HTMLReportBody;

public class HTMLBodyReportBuilder extends HTMLReportBody implements ReportBodyBuilder {

    private HTMLReportBody reportBody;

    public HTMLBodyReportBuilder() {
        reportBody = new HTMLReportBody();
    }

    @Override
    public ReportBodyBuilder setCustomerName(String customerName) {
        reportBody.putContent("<span class=\"customerName\">");
        reportBody.putContent(customerName);
        reportBody.putContent("</span>");

        return this;
    }

    @Override
    public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
        reportBody.putContent("<span class=\"customerPhone\">");
        reportBody.putContent(phoneNumber);
        reportBody.putContent("</span>");

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
        reportBody.putContent("</items>");

        return reportBody;
    }
}
