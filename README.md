
# Scala StringTemplate v4 wrapper

This library is Scala StringTemplate v4 wrapper.

It extends localization/internalization function.
It overwrites some terrible ST4's default settings.
Have a nice ST4 life!

# Usage

sbt

    resolvers += "takezoux2@github" at "http://takezoux2.github.com/maven"

    libraryDependencies := "com.geishatokyo" %% "scala-st4" % "4.0.4"

# Sample code

You prepare .st files.

Template files

    mytemplates/
      t1.st
      t2.st

t1.st

    This is first $name$ template.

t2.st

    This is second $name$ template.


scala code

    import com.geishatokyo.scalast4._

    val group = STGroup.fromEachFileInDir("mytemplates") // can use either relative and absolute path
    val template = group("t1")
    template.add("name" -> "Taro")
    template.render must_== "This is first Taro template"

    //you can write in one liner
    group("t2").add("name" -> "Jiro").render must_== "This is second Jiro template"



# Localization extension


## Template level localization

When you try to get "hoge" template from STGroup and your default locale is Japan, STGroup searches next three templates.

* hoge_ja_JP
* hoge_ja
* hoge

## Word level localization

And also you can collaborate with i18n.

yourTemplate.st

    $ThisWord;format=i18n$ changes according to locale

resourceBundlerName_ja.properties

    fruit=りんご
    animal=犬

resourceBundlerName_en.properties

    fruit=Apple
    animal=Dog

scala code

    val g = STGroup.fromXXX()
    g.registerAttributeRenderer(new I18nStringRenderer("resourceBundlerName"))

    g("yourTemplate",Locale.Japan).add("ThisWord" -> "fruit").render must_== "りんご changes according to locale"
    g("yourTemplate",Locale.Japan).add("ThisWord" -> "animal").render must_== "犬 changes according to locale"

    g("yourTemplate",Locale.English).add("ThisWord" -> "fruit").render must_== "Apple changes according to locale"
    g("yourTemplate",Locale.English).add("ThisWord" -> "animal").render must_== "Dog changes according to locale"



# Overwrite settings

## Default place holder is $

scala-st4 default place holder is "$". Becaus "&lt;" and "&gt;"(which is default place holder for st4) is incompatible with html or xml.

## Always ignore formal args check

If there are extra args, st4 throws new IllegalArgumentException("no such attribute: "+name).
scala-st4 turns off formal args check.

## Throws TemplateNotFoundException if template is not found

ST#apply or ST#getInstanceOf method throw TemplateNotFoundException if template is not found.
If you dislike catching exception, you can use ST.get instead.

