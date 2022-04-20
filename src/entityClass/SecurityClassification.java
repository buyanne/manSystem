package entityClass;


/**
 * @author 卟言呢
 */
public enum SecurityClassification {
    /**
     *@Description
     *@Param
     *@Return
     *@Exception
     **/
    A("A", 1), B("B", 2), C("C", 3), D("D", 4);
    private String name;
    private int index;


    /**
     * @param name 名字
     * @param index 下标
     */
    private SecurityClassification(String name, int index) {
        this.name = name;
        this.index = index;
    }

    SecurityClassification(String name){

    }


    /**
     * @param name 输入的名字
     * @return null
     */
    public static SecurityClassification getSecurityClassfication(String name) {
        for (SecurityClassification sc : SecurityClassification.values()) {
            if (sc.getName().equalsIgnoreCase(name)) {
                return sc;
            }
        }
        return SecurityClassification.D;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
