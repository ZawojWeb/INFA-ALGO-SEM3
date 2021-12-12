package eu.jpereira.trainings.designpatterns.creational.builder.model;

import eu.jpereira.trainings.designpatterns.creational.builder.json.JSONReportBody;

public class JSONBodyReportBuilder extends JSONReportBody implements ReportBodyBuilder {

    private JSONReportBody reportBody;

    public JSONBodyReportBuilder() {
        reportBody = new JSONReportBody();
    }

    @Override
    public ReportBodyBuilder setCustomerName(String customerName) {
        reportBody.addContent("sale:{customer:{");
        reportBody.addContent("name:\"");
        reportBody.addContent(customerName);
        reportBody.addContent("\"");

        return this;
    }

    @Override
    public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
        reportBody.addContent(",phone:\"");
        reportBody.addContent(phoneNumber);
        reportBody.addContent("\"}");

        return this;
    }

    @Override
    public ReportBodyBuilder withItems() {
        reportBody.addContent(",items:[");

        return this;
    }

    @Override
    public ReportBodyBuilder newItem(String name, int quantity, double price) {

        String body = reportBody.getAsString().toString();

        if (body.charAt(body.length()-1) != '[')
            reportBody.addContent(",");

        reportBody.addContent("{name:\"");
        reportBody.addContent(name);
        reportBody.addContent("\",quantity:");
        reportBody.addContent(Integer.toString(quantity));
        reportBody.addContent(",price:");
        reportBody.addContent(Double.toString(price));
        reportBody.addContent("}");

        return this;
    }

    @Override
    public ReportBody getReportBody() {
        reportBody.addContent("]}");

        return reportBody;
    }
}
