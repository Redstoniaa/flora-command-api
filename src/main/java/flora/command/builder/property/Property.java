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

    public B get(CommandBuildInfo<S> info) {
        if (this.isSet()) {
            B value;
            if (brigadierValue != null)
                value = brigadierValue;
            else
                value = getFromFlora(info, floraValue);
            return modifyReturn(info, value);
        } else {
            return null;
        }
    }

    public boolean isSet() {
        return brigadierValue != null || floraValue != null;
    }

    protected abstract B getFromFlora(CommandBuildInfo<S> info, F value);
    protected abstract B modifyReturn(CommandBuildInfo<S> info, B queried);
}