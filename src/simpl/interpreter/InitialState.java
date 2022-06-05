package simpl.interpreter;

import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(), new Mem(), new Int(0));
    }

    private static Env initialEnv() {
        Env new_env = new Env(Env.empty, Symbol.symbol("fst"), new fst());
        new_env = new Env(new_env, Symbol.symbol("snd"), new snd());
        new_env = new Env(new_env, Symbol.symbol("hd"), new hd());
        new_env = new Env(new_env, Symbol.symbol("tl"), new tl());
        new_env = new Env(new_env, Symbol.symbol("iszero"), new iszero());
        new_env = new Env(new_env, Symbol.symbol("pred"), new pred());
        new_env = new Env(new_env, Symbol.symbol("succ"), new succ());
        return new_env;
    }
}
