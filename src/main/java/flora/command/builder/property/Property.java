package flora.command.builder.property;

import flora.command.builder.CommandBuildInfo;

public abstract class Property<S, B, F> {
    public B brigadierValue = null;
    public F floraValue = null;

    public void setBrigadierValue(B value) {
        brigadierValue = value;
        floraValue = null;
    }

    public void setFloraValue(F value) {
        floraValue = value;
        brigadierValue = null;
    }

    private B getRaw(CommandBuildInfo<S> info) {
        if (brigadierValue != null) {
            return brigadierValue;
        } else if (floraValue != null) {
            return getFromFlora(info, floraValue);
        } else {
            return null;
        }
    }

    public B get(CommandBuildInfo<S> info) {
        return modifyReturn(info, getRaw(info));
    }

    protected abstract B getFromFlora(CommandBuildInfo<S> info, F value);
    protected abstract B modifyReturn(CommandBuildInfo<S> info, B queried);
}