package net.mehvahdjukaar.supplementaries.client.renderers.color;

import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HSLColor;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.mehvahdjukaar.supplementaries.reg.ModTextures;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class ColorHelper {
    private static float[][] soapColors;

    public static int pack(float[] rgb) {
        return FastColor.ARGB32.color(255, (int) (rgb[0] * 255), (int) (rgb[1] * 255), (int) (rgb[2] * 255));
    }

    //caps saturation when it's outside the colorspace that has a shape of 2 cones, like that c function describes
    public static float normalizeSaturation(float saturation, float lightness) {
        float c = 1 - Math.abs((2 * lightness) - 1);
        return Math.min(saturation, c);
    }

    public static int getRainbowColorPost(float division) {
        float scale = 3600f / division;
        float h = ((int) ((System.currentTimeMillis()) % (int) scale) / scale);
        return new RGBColor(0xFFAA00).asHCL().withHue(h).asRGB().toInt();
        //HSLColor hsl = prettyfyColor(new HSLColor(h, 0.6f, 0.5f,1));
        //return hsl.asRGB().toInt();
    }

    public static int getRandomBrightColor(RandomSource random) {
        float h = random.nextFloat();
        HSLColor hsl = prettyfyColor(new HSLColor(h, 0.62f + random.nextFloat() * 0.3f, 0.43f + random.nextFloat() * 0.15f,1));
        return hsl.asRGB().toInt();
    }

    public static int getRainbowColor(float division) {
        float scale = 3600f / division;
        float h = ((int) ((System.currentTimeMillis()) % (int) scale) / scale);
        var color = new HSLColor(h, 0.6f, 0.5f, 1);
        return color.asRGB().toInt();
    }

    public static float[] getBubbleColor(float phase) {
        int n = soapColors.length;
        int ind = (int) Math.floor(n * phase);

        float delta = n * phase % 1;

        float[] start = soapColors[ind];
        float[] end = soapColors[(ind + 1) % n];

        float red = Mth.lerp(delta, start[0], end[0]);
        float green = Mth.lerp(delta, start[1], end[1]);
        float blue = Mth.lerp(delta, start[2], end[2]);
        return new float[]{red, green, blue};
    }


    public static HSLColor prettyfyColor(HSLColor hsl) {
        float h = hsl.hue();
        float s = hsl.saturation();
        float l = hsl.lightness();
        //map one to one. no effect on its own (false...)
        //s = s + (float)((1-s)*ClientConfigs.general.TEST3.get());
        s = normalizeSaturation(s, l);

        //remove darker colors
        float minLightness = 0.47f;
        l = Math.max(l, minLightness);

        //saturate dark colors
        float j = (1 - l);
        float ratio = 0.35f;
        if (s < j) s = (ratio * j + (1 - ratio) * s);


        //desaturate blue
        float scaling = 0.15f;
        float angle = 90;
        float n = (float) (scaling * Math.exp(-angle * Math.pow((h - 0.6666f), 2)));
        s -= n;

        return new HSLColor(h, s, l, 1);
    }

    public static void refreshBubbleColors(ResourceManager manager) {
        var c = SpriteUtils. parsePaletteStrip(manager, ResType.TEXTURES.getPath(ModTextures.BUBBLE_BLOCK_COLORS_TEXTURE), 6);
        //int[] c = new int[]{0xd3a4f7, 0xf3c1f0, 0xd3a4f7, 0xa2c0f8, 0xa2f8df, 0xa2c0f8,};
        float[][] temp = new float[c.size()][];
        for (int i = 0; i < c.size(); i++) {
            int j = c.get(i);
            temp[i] = new float[]{FastColor.ABGR32.red(j) / 255f,
                    FastColor.ABGR32.green(j) / 255f, FastColor.ABGR32.blue(j) / 255f};
        }
        soapColors = temp;
    }


}
