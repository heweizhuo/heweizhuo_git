package backTracking;

public class WildcardMatching {
    public static void main(String[] args){
        WildcardMatching w=new WildcardMatching();
        System.out.println(w.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaab","*aa*ba*a*bb*aa*ab*a*aaaaaa*a*aaaa*bbabb*b*b*aaaaaaaaa*a*ba"));
//        System.out.println(w.isMatch("abbaabbbbababaababababbabbbaaaabbbbaaabbbabaabbbbbabbbbabbabbaaabaaaabbbbbbaaabbabbbbababbbaaabbabbabb","***b**a*a*b***b*a*b*bbb**baa*bba**b**bb***b*a*aab*a**"));
        System.out.println(w.isMatch("c","*?*"));
        System.out.println(w.isMatch("abefcdgiescdfimde","ab*cd?i*de"));
        System.out.println(w.isMatch("cb","?a"));
        System.out.println(w.isMatch("adceb","*a*b"));
        System.out.println(w.isMatch("acdcb","a*c?b"));
    }
    public boolean isMatch(String s, String p) {
        p=p.replaceAll("[\\*]+","*");
        return BTSearch(s,p,0,0);
    }
    public boolean BTSearch(String s, String p,int si,int pi){
        if(p.equals("*")) return true;
        if(si==s.length())
            while (pi<p.length() && p.charAt(pi)=='*')pi++;
        if(si==s.length() && pi==p.length()) return true;
        if(si>=s.length() || pi>=p.length())return false;
        boolean match=false;

        if(s.charAt(si)==p.charAt(pi) || p.charAt(pi)=='?') match=BTSearch(s,p,si+1,pi+1);
        if(!match && p.charAt(pi)=='*') {
            if (pi+1<p.length() && p.charAt(pi+1)=='?'){
                match=BTSearch(s,p,si,pi+1);
            }
            if(!match && pi+1<p.length() && p.charAt(pi+1)!='?'){
                while (!match && si<s.length() && pi<p.length()) {
                    while (si < s.length() && pi + 1 < p.length() && s.charAt(si) != p.charAt(pi + 1)) {
                        si++;
                    }
                    match = BTSearch(s, p, si++, pi+1);
                }
            }
        }
        return match;
    }
}
