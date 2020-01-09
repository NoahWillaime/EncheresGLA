/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import dto.Article;
import dto.Promotion;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import manager.ArticleManagerBeanLocal;
import manager.PromotionManagerBeanLocal;

/**
 *
 * @author noahd
 */
@Stateless
public class GeneratePromotionBean implements GeneratePromotionBeanLocal{
    @EJB(name="ArticleManagerBean")
    private ArticleManagerBeanLocal articleManagerBean;    
    
    @EJB(name="PromotionManagerBean")
    private PromotionManagerBeanLocal promotions; 
    
    public boolean isPromo(float chance) {
        Random rand = new Random();
        float generateNb = rand.nextFloat() * (1f - 0f) + 0f;
        return chance <= generateNb;
    }
    
    public float generateReduc() {
        Random rand = new Random();
        float generateNb = rand.nextFloat() * (0.5f - 0.1f) + 0.1f;
        return (float)(Math.round(generateNb * 10) / 10.0);
    }
    
    public Promotion createPromo(boolean transport, Article a) {
        Promotion p;
        if (transport)
            p = new Promotion("Frais de transport", 0, 1);
        else
            p = new Promotion("Bon d'achat", 1, generateReduc());
        p.addArticles(a);
        return promotions.addPromotion(p);
    }
    
    @Schedule(second="0", minute="0", hour="0",
    dayOfMonth="*", month="*", year="*")
    public void calculNewPromos() {
        promotions.deleteAll();
        for (Article a : articleManagerBean.getAll()) {
            if (isPromo(0.6f)) { //40% de chance
                if (isPromo(0.3f)) { // 70% de chance
                    a.addPromotions(createPromo(true, a));
                } else {
                    a.addPromotions(createPromo(false, a));
                }
            }
        }
    }
}
