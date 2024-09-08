package demo;

import java.util.ArrayList;
import java.util.List;

import bases.Human;
import bases.Monster;
import humans.Brave;
import humans.Fighter;
import humans.Wizard;
import monsters.Dragon;
import monsters.Oak;
import monsters.Slime;
import utils.Dice;

public class Main {
    public static void main(String[] args) {

        System.out.println("★★ ==== 戦いの開始だ！！ ==== ★★");

        // Brave（勇者）, Fighter（戦士）, Wizard（魔法使い）クラスの各インスタンスを生成
        Human brave = new Brave("沖田総司", "剣");
        Human fighter = new Fighter("金太郎", "斧");
        Human wizard = new Wizard("安倍晴明", "魔法");

        // 人間グループのリストを空で生成
        List<Human> humans = new ArrayList<>();

        // 勇者、戦士、魔法使いを人間グループのリストに追加
        humans.add(brave);
        humans.add(fighter);
        humans.add(wizard);

        // Slime（スライム）, Oak（オーク）, Dragon（ドラゴン）クラスの各インスタンスを生成
        Monster slime = new Slime("キングスライム", "体当たり");
        Monster oak = new Oak("オークキング", "槍");
        Monster dragon = new Dragon("紅龍", "炎");

        // モンスターグループのリストを空で生成
        List<Monster> monsters = new ArrayList<>();

        // スライム、オーク、ドラゴンをモンスターグループのリストに追加
        monsters.add(slime);
        monsters.add(oak);
        monsters.add(dragon);

        // 現在の各グループの状態を一覧表示
        showGroupInfos(humans, monsters);

        // 第何回戦かを示すカウンター変数
        int count = 1;
        
        // ★
        Human selectedHuman;
        Monster selectedMonster;

        // 勝敗がつくまで無限ループ
        while (true) {

            System.out.printf("\n★ 第%d回戦 ==========\n", count);

            System.out.println("\n[人間のターン！]\n");

            // 人間グループから1人選択
            selectedHuman = choiceHuman(humans);

            // モンスターグループから1人選択
            selectedMonster = choiceMonster(monsters);

            // 選ばれた人間が、選ばれたモンスターを攻撃
            selectedHuman.attack(selectedMonster);

            // モンスターのHPが0以下になれば、モンスターは倒れ、そのモンスターをモンスターグループから削除
            if (selectedMonster.getHp() <= 0) {
                System.out.printf("\n ★「%s」 は倒れた！\n", selectedMonster.getName());
                monsters.remove(selectedMonster);
            }

            // モンスターグループに誰もいなくなれば、人間グループの勝利
            if (monsters.isEmpty()) {
                System.out.print("\n★★ ==== 決着がついた！！ ==== ★★\n");
                
                System.out.print("\n #### 人間達は勝利した！！ ####\n");
                break;
            }

            System.out.println("\n[モンスターのターン！]\n");

            // 人間グループから1人選択
            selectedHuman = choiceHuman(humans);

            // モンスターグループから1人選択
            selectedMonster = choiceMonster(monsters);

            // 選ばれたモンスターが、選ばれた人間を攻撃
            selectedMonster.attack(selectedHuman);

            // 人間のHPが0以下になれば、人間は倒れ、その人間を人間グループから削除
            if (selectedHuman.getHp() <= 0) {
                System.out.printf("\n ★「%s」 は倒れた！\n", selectedHuman.getName());
                humans.remove(selectedHuman);
            }

            // 人間グループに誰もいなくなれば、人間グループの敗北
            if (humans.isEmpty()) {
            	System.out.print("\n★★ ==== 決着がついた！！ ==== ★★\n");
            	
            	System.out.print("\n #### 人間達は敗北した、、 ####\n");
                break;
            }

            // 現在の各グループの状態を一覧表示
            showGroupInfos(humans, monsters);

            // ループ変数を1増やす
            count++;
        }

        // 最後に各グループの状態を一覧表示してプログラム終了
        showGroupInfos(humans, monsters);
    }

    // 引数でもらった人間グループリストからランダムに1人を選択し、その結果を戻り値とするメソッド
    public static Human choiceHuman(List<Human> humans) {
        Human human = humans.get(Dice.get(0, humans.size() - 1));
        System.out.printf("人間グループから 「%s」 のお出ましだ！\n", human.getName());
        return human;
    }

    // 引数でもらったモンスターグループリストからランダムに1人を選択し、その結果を戻り値とするメソッド
    public static Monster choiceMonster(List<Monster> monsters) {
        Monster monster = monsters.get(Dice.get(0, monsters.size() - 1));
        System.out.printf("モンスターグループから 「%s」 のお出ましだ！\n", monster.getName());
        return monster;
    }

    // 引数でもらった人間グループリストとモンスターグループリストのそれぞれの情報一覧を表示するメソッド
    public static void showGroupInfos(List<Human> humans, List<Monster> monsters) {

        System.out.println("\n## === グループ情報 === ##");
        System.out.printf("# [人間グループ]: %d人\n", humans.size());
        for (Human human : humans) {
            System.out.println(human);
        }

        System.out.printf("\n# [モンスターグループ]: %d人\n", monsters.size());
        for (Monster monster : monsters) {
            System.out.println(monster);
        }
    }

}