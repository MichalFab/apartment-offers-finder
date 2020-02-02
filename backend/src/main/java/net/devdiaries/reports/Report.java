package net.devdiaries.reports;
import java.util.Objects;
public class Report {

    public Report(String data) {
        this.data = data;
    }

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(data, report.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Report{" +
                "data='" + data + '\'' +
                '}';
    }
}
