/*
 *    StopwordsEnglish.java
 *    Copyright (C) 2001 Eibe Frank
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package kea;

import java.util.*;

/**
 * Class that can test whether a given string is a stop word.
 * Lowercases all words before the test.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version 1.0
 */
@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class StopwordsEnglish extends Stopwords {

    /**
     * The hashtable containing the list of stopwords
     */
    private static Hashtable m_Stopwords = null;

    static {

        if (m_Stopwords == null) {
            m_Stopwords = new Hashtable();
            Double dummy = new Double(0);

            m_Stopwords.put("a", dummy);
            m_Stopwords.put("abaft", dummy);
            m_Stopwords.put("aboard", dummy);
            m_Stopwords.put("about", dummy);
            m_Stopwords.put("above", dummy);
            m_Stopwords.put("across", dummy);
            m_Stopwords.put("afore", dummy);
            m_Stopwords.put("aforesaid", dummy);
            m_Stopwords.put("after", dummy);
            m_Stopwords.put("again", dummy);
            m_Stopwords.put("against", dummy);
            m_Stopwords.put("agin", dummy);
            m_Stopwords.put("ago", dummy);
            m_Stopwords.put("aint", dummy);
            m_Stopwords.put("albeit", dummy);
            m_Stopwords.put("all", dummy);
            m_Stopwords.put("almost", dummy);
            m_Stopwords.put("alone", dummy);
            m_Stopwords.put("along", dummy);
            m_Stopwords.put("alongside", dummy);
            m_Stopwords.put("already", dummy);
            m_Stopwords.put("also", dummy);
            m_Stopwords.put("although", dummy);
            m_Stopwords.put("always", dummy);
            m_Stopwords.put("am", dummy);
            m_Stopwords.put("american", dummy);
            m_Stopwords.put("amid", dummy);
            m_Stopwords.put("amidst", dummy);
            m_Stopwords.put("among", dummy);
            m_Stopwords.put("amongst", dummy);
            m_Stopwords.put("an", dummy);
            m_Stopwords.put("and", dummy);
            m_Stopwords.put("anent", dummy);
            m_Stopwords.put("another", dummy);
            m_Stopwords.put("any", dummy);
            m_Stopwords.put("anybody", dummy);
            m_Stopwords.put("anyone", dummy);
            m_Stopwords.put("anything", dummy);
            m_Stopwords.put("are", dummy);
            m_Stopwords.put("aren't", dummy);
            m_Stopwords.put("around", dummy);
            m_Stopwords.put("as", dummy);
            m_Stopwords.put("aslant", dummy);
            m_Stopwords.put("astride", dummy);
            m_Stopwords.put("at", dummy);
            m_Stopwords.put("athwart", dummy);
            m_Stopwords.put("away", dummy);
            m_Stopwords.put("b", dummy);
            m_Stopwords.put("back", dummy);
            m_Stopwords.put("bar", dummy);
            m_Stopwords.put("barring", dummy);
            m_Stopwords.put("be", dummy);
            m_Stopwords.put("because", dummy);
            m_Stopwords.put("been", dummy);
            m_Stopwords.put("before", dummy);
            m_Stopwords.put("behind", dummy);
            m_Stopwords.put("being", dummy);
            m_Stopwords.put("below", dummy);
            m_Stopwords.put("beneath", dummy);
            m_Stopwords.put("beside", dummy);
            m_Stopwords.put("besides", dummy);
            m_Stopwords.put("best", dummy);
            m_Stopwords.put("better", dummy);
            m_Stopwords.put("between", dummy);
            m_Stopwords.put("betwixt", dummy);
            m_Stopwords.put("beyond", dummy);
            m_Stopwords.put("both", dummy);
            m_Stopwords.put("but", dummy);
            m_Stopwords.put("by", dummy);
            m_Stopwords.put("c", dummy);
            m_Stopwords.put("can", dummy);
            m_Stopwords.put("cannot", dummy);
            m_Stopwords.put("can't", dummy);
            m_Stopwords.put("certain", dummy);
            m_Stopwords.put("circa", dummy);
            m_Stopwords.put("close", dummy);
            m_Stopwords.put("concerning", dummy);
            m_Stopwords.put("considering", dummy);
            m_Stopwords.put("cos", dummy);
            m_Stopwords.put("could", dummy);
            m_Stopwords.put("couldn't", dummy);
            m_Stopwords.put("couldst", dummy);
            m_Stopwords.put("d", dummy);
            m_Stopwords.put("dare", dummy);
            m_Stopwords.put("dared", dummy);
            m_Stopwords.put("daren't", dummy);
            m_Stopwords.put("dares", dummy);
            m_Stopwords.put("daring", dummy);
            m_Stopwords.put("despite", dummy);
            m_Stopwords.put("did", dummy);
            m_Stopwords.put("didn't", dummy);
            m_Stopwords.put("different", dummy);
            m_Stopwords.put("directly", dummy);
            m_Stopwords.put("do", dummy);
            m_Stopwords.put("does", dummy);
            m_Stopwords.put("doesn't", dummy);
            m_Stopwords.put("doing", dummy);
            m_Stopwords.put("done", dummy);
            m_Stopwords.put("don't", dummy);
            m_Stopwords.put("dost", dummy);
            m_Stopwords.put("doth", dummy);
            m_Stopwords.put("down", dummy);
            m_Stopwords.put("during", dummy);
            m_Stopwords.put("durst", dummy);
            m_Stopwords.put("e", dummy);
            m_Stopwords.put("each", dummy);
            m_Stopwords.put("early", dummy);
            m_Stopwords.put("either", dummy);
            m_Stopwords.put("em", dummy);
            m_Stopwords.put("english", dummy);
            m_Stopwords.put("enough", dummy);
            m_Stopwords.put("ere", dummy);
            m_Stopwords.put("even", dummy);
            m_Stopwords.put("ever", dummy);
            m_Stopwords.put("every", dummy);
            m_Stopwords.put("everybody", dummy);
            m_Stopwords.put("everyone", dummy);
            m_Stopwords.put("everything", dummy);
            m_Stopwords.put("except", dummy);
            m_Stopwords.put("excepting", dummy);
            m_Stopwords.put("f", dummy);
            m_Stopwords.put("failing", dummy);
            m_Stopwords.put("far", dummy);
            m_Stopwords.put("few", dummy);
            m_Stopwords.put("first", dummy);
            m_Stopwords.put("five", dummy);
            m_Stopwords.put("following", dummy);
            m_Stopwords.put("for", dummy);
            m_Stopwords.put("four", dummy);
            m_Stopwords.put("from", dummy);
            m_Stopwords.put("g", dummy);
            m_Stopwords.put("gonna", dummy);
            m_Stopwords.put("gotta", dummy);
            m_Stopwords.put("h", dummy);
            m_Stopwords.put("had", dummy);
            m_Stopwords.put("hadn't", dummy);
            m_Stopwords.put("hard", dummy);
            m_Stopwords.put("has", dummy);
            m_Stopwords.put("hasn't", dummy);
            m_Stopwords.put("hast", dummy);
            m_Stopwords.put("hath", dummy);
            m_Stopwords.put("have", dummy);
            m_Stopwords.put("haven't", dummy);
            m_Stopwords.put("having", dummy);
            m_Stopwords.put("he", dummy);
            m_Stopwords.put("he'd", dummy);
            m_Stopwords.put("he'll", dummy);
            m_Stopwords.put("her", dummy);
            m_Stopwords.put("here", dummy);
            m_Stopwords.put("here's", dummy);
            m_Stopwords.put("hers", dummy);
            m_Stopwords.put("herself", dummy);
            m_Stopwords.put("he's", dummy);
            m_Stopwords.put("high", dummy);
            m_Stopwords.put("him", dummy);
            m_Stopwords.put("himself", dummy);
            m_Stopwords.put("his", dummy);
            m_Stopwords.put("home", dummy);
            m_Stopwords.put("how", dummy);
            m_Stopwords.put("howbeit", dummy);
            m_Stopwords.put("however", dummy);
            m_Stopwords.put("how's", dummy);
            m_Stopwords.put("i", dummy);
            m_Stopwords.put("id", dummy);
            m_Stopwords.put("if", dummy);
            m_Stopwords.put("ill", dummy);
            m_Stopwords.put("i'm", dummy);
            m_Stopwords.put("immediately", dummy);
            m_Stopwords.put("important", dummy);
            m_Stopwords.put("in", dummy);
            m_Stopwords.put("inside", dummy);
            m_Stopwords.put("instantly", dummy);
            m_Stopwords.put("into", dummy);
            m_Stopwords.put("is", dummy);
            m_Stopwords.put("isn't", dummy);
            m_Stopwords.put("it", dummy);
            m_Stopwords.put("it'll", dummy);
            m_Stopwords.put("it's", dummy);
            m_Stopwords.put("its", dummy);
            m_Stopwords.put("itself", dummy);
            m_Stopwords.put("i've", dummy);
            m_Stopwords.put("j", dummy);
            m_Stopwords.put("just", dummy);
            m_Stopwords.put("k", dummy);
            m_Stopwords.put("l", dummy);
            m_Stopwords.put("large", dummy);
            m_Stopwords.put("last", dummy);
            m_Stopwords.put("later", dummy);
            m_Stopwords.put("least", dummy);
            m_Stopwords.put("left", dummy);
            m_Stopwords.put("less", dummy);
            m_Stopwords.put("lest", dummy);
            m_Stopwords.put("let's", dummy);
            m_Stopwords.put("like", dummy);
            m_Stopwords.put("likewise", dummy);
            m_Stopwords.put("little", dummy);
            m_Stopwords.put("living", dummy);
            m_Stopwords.put("long", dummy);
            m_Stopwords.put("m", dummy);
            m_Stopwords.put("many", dummy);
            m_Stopwords.put("may", dummy);
            m_Stopwords.put("mayn't", dummy);
            m_Stopwords.put("me", dummy);
            m_Stopwords.put("mid", dummy);
            m_Stopwords.put("midst", dummy);
            m_Stopwords.put("might", dummy);
            m_Stopwords.put("mightn't", dummy);
            m_Stopwords.put("mine", dummy);
            m_Stopwords.put("minus", dummy);
            m_Stopwords.put("more", dummy);
            m_Stopwords.put("most", dummy);
            m_Stopwords.put("much", dummy);
            m_Stopwords.put("must", dummy);
            m_Stopwords.put("mustn't", dummy);
            m_Stopwords.put("my", dummy);
            m_Stopwords.put("myself", dummy);
            m_Stopwords.put("n", dummy);
            m_Stopwords.put("near", dummy);
            m_Stopwords.put("'neath", dummy);
            m_Stopwords.put("need", dummy);
            m_Stopwords.put("needed", dummy);
            m_Stopwords.put("needing", dummy);
            m_Stopwords.put("needn't", dummy);
            m_Stopwords.put("needs", dummy);
            m_Stopwords.put("neither", dummy);
            m_Stopwords.put("never", dummy);
            m_Stopwords.put("nevertheless", dummy);
            m_Stopwords.put("new", dummy);
            m_Stopwords.put("next", dummy);
            m_Stopwords.put("nigh", dummy);
            m_Stopwords.put("nigher", dummy);
            m_Stopwords.put("nighest", dummy);
            m_Stopwords.put("nisi", dummy);
            m_Stopwords.put("no", dummy);
            m_Stopwords.put("no-one", dummy);
            m_Stopwords.put("nobody", dummy);
            m_Stopwords.put("none", dummy);
            m_Stopwords.put("nor", dummy);
            m_Stopwords.put("not", dummy);
            m_Stopwords.put("nothing", dummy);
            m_Stopwords.put("notwithstanding", dummy);
            m_Stopwords.put("now", dummy);
            m_Stopwords.put("o", dummy);
            m_Stopwords.put("o'er", dummy);
            m_Stopwords.put("of", dummy);
            m_Stopwords.put("off", dummy);
            m_Stopwords.put("often", dummy);
            m_Stopwords.put("on", dummy);
            m_Stopwords.put("once", dummy);
            m_Stopwords.put("one", dummy);
            m_Stopwords.put("oneself", dummy);
            m_Stopwords.put("only", dummy);
            m_Stopwords.put("onto", dummy);
            m_Stopwords.put("open", dummy);
            m_Stopwords.put("or", dummy);
            m_Stopwords.put("other", dummy);
            m_Stopwords.put("otherwise", dummy);
            m_Stopwords.put("ought", dummy);
            m_Stopwords.put("oughtn't", dummy);
            m_Stopwords.put("our", dummy);
            m_Stopwords.put("ours", dummy);
            m_Stopwords.put("ourselves", dummy);
            m_Stopwords.put("out", dummy);
            m_Stopwords.put("outside", dummy);
            m_Stopwords.put("over", dummy);
            m_Stopwords.put("own", dummy);
            m_Stopwords.put("p", dummy);
            m_Stopwords.put("past", dummy);
            m_Stopwords.put("pending", dummy);
            m_Stopwords.put("per", dummy);
            m_Stopwords.put("perhaps", dummy);
            m_Stopwords.put("plus", dummy);
            m_Stopwords.put("possible", dummy);
            m_Stopwords.put("present", dummy);
            m_Stopwords.put("probably", dummy);
            m_Stopwords.put("provided", dummy);
            m_Stopwords.put("providing", dummy);
            m_Stopwords.put("public", dummy);
            m_Stopwords.put("q", dummy);
            m_Stopwords.put("qua", dummy);
            m_Stopwords.put("quite", dummy);
            m_Stopwords.put("r", dummy);
            m_Stopwords.put("rather", dummy);
            m_Stopwords.put("re", dummy);
            m_Stopwords.put("real", dummy);
            m_Stopwords.put("really", dummy);
            m_Stopwords.put("respecting", dummy);
            m_Stopwords.put("right", dummy);
            m_Stopwords.put("round", dummy);
            m_Stopwords.put("s", dummy);
            m_Stopwords.put("same", dummy);
            m_Stopwords.put("sans", dummy);
            m_Stopwords.put("save", dummy);
            m_Stopwords.put("saving", dummy);
            m_Stopwords.put("second", dummy);
            m_Stopwords.put("several", dummy);
            m_Stopwords.put("shall", dummy);
            m_Stopwords.put("shalt", dummy);
            m_Stopwords.put("shan't", dummy);
            m_Stopwords.put("she", dummy);
            m_Stopwords.put("shed", dummy);
            m_Stopwords.put("shell", dummy);
            m_Stopwords.put("she's", dummy);
            m_Stopwords.put("short", dummy);
            m_Stopwords.put("should", dummy);
            m_Stopwords.put("shouldn't", dummy);
            m_Stopwords.put("since", dummy);
            m_Stopwords.put("six", dummy);
            m_Stopwords.put("small", dummy);
            m_Stopwords.put("so", dummy);
            m_Stopwords.put("some", dummy);
            m_Stopwords.put("somebody", dummy);
            m_Stopwords.put("someone", dummy);
            m_Stopwords.put("something", dummy);
            m_Stopwords.put("sometimes", dummy);
            m_Stopwords.put("soon", dummy);
            m_Stopwords.put("special", dummy);
            m_Stopwords.put("still", dummy);
            m_Stopwords.put("such", dummy);
            m_Stopwords.put("summat", dummy);
            m_Stopwords.put("supposing", dummy);
            m_Stopwords.put("sure", dummy);
            m_Stopwords.put("t", dummy);
            m_Stopwords.put("than", dummy);
            m_Stopwords.put("that", dummy);
            m_Stopwords.put("that'd", dummy);
            m_Stopwords.put("that'll", dummy);
            m_Stopwords.put("that's", dummy);
            m_Stopwords.put("the", dummy);
            m_Stopwords.put("thee", dummy);
            m_Stopwords.put("their", dummy);
            m_Stopwords.put("theirs", dummy);
            m_Stopwords.put("their's", dummy);
            m_Stopwords.put("them", dummy);
            m_Stopwords.put("themselves", dummy);
            m_Stopwords.put("then", dummy);
            m_Stopwords.put("there", dummy);
            m_Stopwords.put("there's", dummy);
            m_Stopwords.put("these", dummy);
            m_Stopwords.put("they", dummy);
            m_Stopwords.put("they'd", dummy);
            m_Stopwords.put("they'll", dummy);
            m_Stopwords.put("they're", dummy);
            m_Stopwords.put("they've", dummy);
            m_Stopwords.put("thine", dummy);
            m_Stopwords.put("this", dummy);
            m_Stopwords.put("tho", dummy);
            m_Stopwords.put("those", dummy);
            m_Stopwords.put("thou", dummy);
            m_Stopwords.put("though", dummy);
            m_Stopwords.put("three", dummy);
            m_Stopwords.put("thro'", dummy);
            m_Stopwords.put("through", dummy);
            m_Stopwords.put("throughout", dummy);
            m_Stopwords.put("thru", dummy);
            m_Stopwords.put("thyself", dummy);
            m_Stopwords.put("till", dummy);
            m_Stopwords.put("to", dummy);
            m_Stopwords.put("today", dummy);
            m_Stopwords.put("together", dummy);
            m_Stopwords.put("too", dummy);
            m_Stopwords.put("touching", dummy);
            m_Stopwords.put("toward", dummy);
            m_Stopwords.put("towards", dummy);
            m_Stopwords.put("true", dummy);
            m_Stopwords.put("'twas", dummy);
            m_Stopwords.put("'tween", dummy);
            m_Stopwords.put("'twere", dummy);
            m_Stopwords.put("'twill", dummy);
            m_Stopwords.put("'twixt", dummy);
            m_Stopwords.put("two", dummy);
            m_Stopwords.put("'twould", dummy);
            m_Stopwords.put("u", dummy);
            m_Stopwords.put("under", dummy);
            m_Stopwords.put("underneath", dummy);
            m_Stopwords.put("unless", dummy);
            m_Stopwords.put("unlike", dummy);
            m_Stopwords.put("until", dummy);
            m_Stopwords.put("unto", dummy);
            m_Stopwords.put("up", dummy);
            m_Stopwords.put("upon", dummy);
            m_Stopwords.put("us", dummy);
            m_Stopwords.put("used", dummy);
            m_Stopwords.put("usually", dummy);
            m_Stopwords.put("v", dummy);
            m_Stopwords.put("versus", dummy);
            m_Stopwords.put("very", dummy);
            m_Stopwords.put("via", dummy);
            m_Stopwords.put("vice", dummy);
            m_Stopwords.put("vis-a-vis", dummy);
            m_Stopwords.put("w", dummy);
            m_Stopwords.put("wanna", dummy);
            m_Stopwords.put("wanting", dummy);
            m_Stopwords.put("was", dummy);
            m_Stopwords.put("wasn't", dummy);
            m_Stopwords.put("way", dummy);
            m_Stopwords.put("we", dummy);
            m_Stopwords.put("we'd", dummy);
            m_Stopwords.put("well", dummy);
            m_Stopwords.put("were", dummy);
            m_Stopwords.put("weren't", dummy);
            m_Stopwords.put("wert", dummy);
            m_Stopwords.put("we've", dummy);
            m_Stopwords.put("what", dummy);
            m_Stopwords.put("whatever", dummy);
            m_Stopwords.put("what'll", dummy);
            m_Stopwords.put("what's", dummy);
            m_Stopwords.put("when", dummy);
            m_Stopwords.put("whencesoever", dummy);
            m_Stopwords.put("whenever", dummy);
            m_Stopwords.put("when's", dummy);
            m_Stopwords.put("whereas", dummy);
            m_Stopwords.put("where's", dummy);
            m_Stopwords.put("whether", dummy);
            m_Stopwords.put("which", dummy);
            m_Stopwords.put("whichever", dummy);
            m_Stopwords.put("whichsoever", dummy);
            m_Stopwords.put("while", dummy);
            m_Stopwords.put("whilst", dummy);
            m_Stopwords.put("who", dummy);
            m_Stopwords.put("who'd", dummy);
            m_Stopwords.put("whoever", dummy);
            m_Stopwords.put("whole", dummy);
            m_Stopwords.put("who'll", dummy);
            m_Stopwords.put("whom", dummy);
            m_Stopwords.put("whore", dummy);
            m_Stopwords.put("who's", dummy);
            m_Stopwords.put("whose", dummy);
            m_Stopwords.put("whoso", dummy);
            m_Stopwords.put("whosoever", dummy);
            m_Stopwords.put("will", dummy);
            m_Stopwords.put("with", dummy);
            m_Stopwords.put("within", dummy);
            m_Stopwords.put("without", dummy);
            m_Stopwords.put("wont", dummy);
            m_Stopwords.put("would", dummy);
            m_Stopwords.put("wouldn't", dummy);
            m_Stopwords.put("wouldst", dummy);
            m_Stopwords.put("x", dummy);
            m_Stopwords.put("y", dummy);
            m_Stopwords.put("ye", dummy);
            m_Stopwords.put("yet", dummy);
            m_Stopwords.put("you", dummy);
            m_Stopwords.put("you'd", dummy);
            m_Stopwords.put("you'll", dummy);
            m_Stopwords.put("your", dummy);
            m_Stopwords.put("you're", dummy);
            m_Stopwords.put("yours", dummy);
            m_Stopwords.put("yourself", dummy);
            m_Stopwords.put("yourselves", dummy);
            m_Stopwords.put("you've", dummy);
            m_Stopwords.put("z", dummy);
        }
    }

    /**
     * Returns true if the given string is a stop word.
     */
    public boolean isStopword(String str) {

        return m_Stopwords.containsKey(str.toLowerCase());
    }
}


