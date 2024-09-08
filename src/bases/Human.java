package bases;

// 人間を表現する抽象クラス
public abstract class Human extends Living {

    // コンストラクタ
    public Human(String name, String weapon) {
        // Livingクラスで定義したコンストラクタを利用する
        super(name, weapon);
    }

    // attackメソッドのオーバーライド
    @Override
    public void attack(Living target) {
        // 1から10までのサイコロを振り、自分の攻撃力とかけ合わせた値を相手に与えるダメージとする
        int diceRoll = utils.Dice.get(1, 10);
        int damage = this.offensive * diceRoll;

        // 相手のHPをダメージ値だけ減らす
        target.setHp(target.getHp() - damage);

        // 自分の攻撃力を1だけ減らす
        this.offensive-- ;

        // コンソールにステータスを表示
        System.out.printf("\n「%s」が「%s」で攻撃！「%s」に%dのダメージを与えた。\n", this.name, this.weapon, target.getName(), damage);
        System.out.println("しかし自分の攻撃力も1減少した。");
    }
}