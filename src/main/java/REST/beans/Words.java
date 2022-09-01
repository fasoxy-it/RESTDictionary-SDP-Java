package REST.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Words {

    @XmlElement(name = "my_dictionary")
    private List<Word> dictionary;

    private static Words instance;

    private Words() { dictionary = new ArrayList<Word>(); }

    public synchronized static Words getInstance(){
        if(instance==null)
            instance = new Words();
        return instance;
    }

    public synchronized List<Word> getDictionary() {
        return new ArrayList<>(dictionary);
    }

    public synchronized void add(Word w) { dictionary.add(w); }

    public synchronized void change(Word w) {
        for (Word wr: getDictionary()) {
            if (wr.getWord().equalsIgnoreCase(w.getWord())) {
                wr.setDefinition(w.getDefinition());
            }
        }
    }

    public Word getDefinition(String w) {
        List<Word> wordsCopy = getDictionary();
        for (Word wr: wordsCopy) {
            if (wr.getWord().equalsIgnoreCase(w)) {
                return wr;
            }
        }
        return null;
    }

    public synchronized void delete(String w) {
        for (Word wr: getDictionary()) {
            if (wr.getWord().equalsIgnoreCase(w)) {
                dictionary.remove(wr);
            }
        }
    }

}
