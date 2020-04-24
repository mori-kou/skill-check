package q005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(openDataFile(), StandardCharsets.UTF_8))) {

            // 社員番号,部署名,役職名,P-CODE,作業時間
            List<WorkData> data = new ArrayList<>();
            String line;
            for (int index = 0; (line = reader.readLine()) != null; index++) {
                if (index != 0) {
                    WorkData workData = new WorkData(line.split(","));
                    data.add(workData);
                }
            }
            // 役職別に出力
            Map<String, Integer> totalWorkTimeByPosition = data.stream().collect(
                    Collectors.groupingBy(WorkData::getPosition, LinkedHashMap::new,
                            Collectors.summingInt(WorkData::getWorkTime)));
            outputTotalWorkTime(totalWorkTimeByPosition);

            // Pコード別に出力
            Map<String, Integer> totalWorkTimeByPcode = data.stream().collect(
                    Collectors.groupingBy(WorkData::getPCode, LinkedHashMap::new,
                            Collectors.summingInt(WorkData::getWorkTime)));
            outputTotalWorkTime(totalWorkTimeByPcode);

            // 社員番号別に出力
            Map<String, Integer> totalWorkTimeByNumber = data.stream().collect(
                    Collectors.groupingBy(WorkData::getNumber, LinkedHashMap::new,
                            Collectors.summingInt(WorkData::getWorkTime)));
            outputTotalWorkTime(totalWorkTimeByNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    private static void outputTotalWorkTime(Map<String, Integer> totalWorkTimeByKey) {
        totalWorkTimeByKey.entrySet().stream()
                .map(e -> e.getKey() + ": " + convertTimeNotation(e.getValue()))
                .forEach(System.out::println);
    }

    private static String convertTimeNotation(Integer min) {
        return (min / 60) + "時間" + (min % 60) + "分";
    }
}
// 完成までの時間: 1時間 20分