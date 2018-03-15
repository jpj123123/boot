package jpj.boot.entity;

import java.util.List;

public class TEnum {
    private Long id;

    private Long pid;

    private String code;

    private String name;

    private String icons;

    private Byte type;

    private String url;

    private Integer desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons == null ? null : icons.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getDesc() {
        return desc;
    }

    public void setDesc(Integer desc) {
        this.desc = desc;
    }

    private List<TEnum> children;

    public List<TEnum> getChildren() {
        return children;
    }

    public void setChildren(List<TEnum> children) {
        this.children = children;
    }
}