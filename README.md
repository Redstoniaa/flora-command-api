# Fabric Template Mod
This is just an unofficial Fabric template mod you can use for your own needs!

## Setup
For setup instructions please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the IDE that you are using. You'll also want to do the following to make the mod your own:
1. Go into the `fabric.mod.json` file (you can find this in the `resources` folder) and edit the relevant information to suit your needs. You should rename every "template" to whatever your mod is named. You may wish to read [this](https://fabricmc.net/wiki/documentation:fabric_mod_json) and [this](https://fabricmc.net/wiki/documentation:fabric_mod_json_spec) if you need more specific details how the file is structured.
2. If you don't plan to use mixins, just delete the `template.mixins.json` file, and delete the reference to it in the `fabric.mod.json` file. If you do, ~~you're cool~~ just rename it like everything else.
3. You probably won't need to go into `build.gradle` unless you need another mod to use as a dependency, or if you're doing smart people Loom magic. 
4. Pop into `gradle.properties` and update the properties and dependencies as required. After that, edit the mod properties to your needs.
5. Go into the `java/template/TemplateMod` and put your mod id into well, where it looks like it goes. Rename every instance of the word "template" in sight.
6. At this point, it's best to commit all your changes. Once you've done this, you can go onto the GitHub thing for your project, and edit the `LICENSE`. This allows you to get into the nice and dandy `Choose a license template` button. Very much recommended step, as keeping your thing under CC0 basically means _everyone can rip you off and cause you grief and suffering._ Probably far overstating it, but still. You should also now edit the `license` in your `fabric.mod.json` to match this.
7. You can get rid of this README now, seeing as you _probably_ aren't going to make a template mod yourself. I shouldn't assume though.

## License
This template is available under the CC0 license. This means this template can actually server its purpose as a _template you can use for yourself without worrying about being sued by MEEEE!_