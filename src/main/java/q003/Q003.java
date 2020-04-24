package q003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(openDataFile(), StandardCharsets.UTF_8))) {

            List<String> data = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                data.addAll(Arrays.asList(line.split(" ")));
            }
            Map<String, Integer> map = data.stream()
                    .map(d -> d.replaceAll("[^a-zA-Z]", ""))
                    .filter(d -> !d.isEmpty()) // 空文字を除去
                    .map(Q003::convertTolowerCase)
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.summingInt(s -> 1))
                    );

            Arrays.stream(map.keySet().toArray())
                    .sorted()
                    .forEach(m ->System.out.println(m + "=" + map.get(m)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertTolowerCase(String value) {
        if (value.equals("I")) {
            return value;
        } else {
            return value.toLowerCase();
        }
    }
}
// 完成までの時間: 0時間 45分