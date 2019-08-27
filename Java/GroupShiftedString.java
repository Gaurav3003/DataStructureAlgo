package Java;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-27
 * Description: https://www.geeksforgeeks.org/group-shifted-string/
 * Given an array of strings (all lowercase letters), the task is to group them in such a way that all strings in a group are shifted versions of each other. Two string S and T are called shifted if,
 * <p>
 * S.length = T.length
 * and
 * S[i] = T[i] + K for
 * 1 <= i <= S.length  for a constant integer K
 * For example strings {acd, dfg, wyz, yab, mop} are shifted versions of each other.
 * <p>
 * Input  : str[] = {"acd", "dfg", "wyz", "yab", "mop",
 * "bdfh", "a", "x", "moqs"};
 * <p>
 * Output : a x
 * acd dfg wyz yab mop
 * bdfh moqs
 * All shifted strings are grouped together.
 * <p>
 * Similar to {@link Java.companyWise.Amazon.GroupIsomorphicStrings}
 * The only change here is the string are "shifted"
 * so we need to change our hash function; Since the hash function in above will generate
 * same hash for
 * meoqs [12345] and bedfh [12345] But they are not shifted string
 */
public class GroupShiftedString {

    public static void main(String[] args) {
        test(Arrays.asList("acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs")); //multiple groups
        test(Arrays.asList("acd", "dfg", "weyz", "yab", "mop", "bedfh", "a", "x", "meoqs")); //multiple groups
        test(Arrays.asList("aaa", "bbd", "weyz", "yab", "mop", "bedfh", "a", "x", "meoqs")); //multiple groups
        test(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg")); //Single group
        test(Arrays.asList("aau", "bbv", "ccw", "ddx", "eey", "ffz")); //Single group
        test(Arrays.asList("aau", "bbw", "ccp", "ddo", "eey", "ffl")); //n  groups

    }

    private static void test(List<String> shiftedStrings) {
        System.out.println("\nInput :" + shiftedStrings);
        System.out.println("Group shifted strings :" + groupShiftedString(shiftedStrings));
    }

    public static Collection<List<String>> groupShiftedString(List<String> shiftedStrings) {

        if (null == shiftedStrings || shiftedStrings.isEmpty())
            return Collections.EMPTY_LIST;

        Map<String, List<String>> hashToShiftedStrings = new HashMap<>();

        for (String shiftedString : shiftedStrings) {

            String hash = hash(shiftedString);

            if (!hashToShiftedStrings.containsKey(hash))
                hashToShiftedStrings.put(hash, new ArrayList<>());

            hashToShiftedStrings.get(hash).add(shiftedString);
        }
        return hashToShiftedStrings.values();

    }

    /**
     * Mapping
     * a->1 ,b->2.....z->26
     *
     * <p>
     * acd -> (c-a)(d-c) -> 21
     * dfg -> (f-d)(g-f) -> 21
     * <p>
     * But careful for case
     * yab -> (a-y)(b-a) ; here (a-y) will result -24,
     * for a negative, we should round them back, so -24 + 26 = 2
     *
     * @param shiftedString
     * @return
     */
    private static String hash(String shiftedString) {

        if (shiftedString.isEmpty())
            return "";

        StringBuilder hash = new StringBuilder();

        for (int i = 1; i < shiftedString.length(); i++) {

            int shift = shiftedString.charAt(i) - shiftedString.charAt(i - 1);

            if (shift < 0)
                shift += 26;

            hash.append(shift);

        }
        return hash.toString();
    }


}
