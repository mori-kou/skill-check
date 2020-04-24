package q005;

/**
 * 作業時間管理クラス
 * 自由に修正してかまいません
 */
public class WorkData {
    /** 社員番号 */
    private String number;

    /** 部署 */
    private String department;

    /** 役職 */
    private String position;

    /** Pコード */
    private String pCode;

    /** 作業時間(分) */
    private int workTime;

    public WorkData(String[] line) {
        number = line[0];
        department = line[1];
        position = line[2];
        pCode = line[3];
        workTime = Integer.parseInt(line[4]);
    }

    public String getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getPCode() {
        return pCode;
    }

    public int getWorkTime() {
        return workTime;
    }
}
