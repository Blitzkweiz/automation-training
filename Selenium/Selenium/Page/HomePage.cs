using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Internal;
using OpenQA.Selenium.Support.UI;
using SeleniumExtras.PageObjects;

namespace Selenium.Page
{
    public class HomePage
    {
        public const string PICKUPTIME = "12";
        public const string DROPOFFTIME = "14";
        public const string DESTINATION = "Colo";
        public const string LOCATION = "Denv";
        public const string COUNTRYOFRESIDENCE = "Austr";
        
        public const string XPATHDROPOFFDATE = "/html/body/div[5]/div[2]/div/div[2]/div/span[35]";
        public const string XPATHDROPOFFDATE2 = "//*[@aria-label=\"November 30, 2019\"]";
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"data-retirada\"]")]
        public IWebElement PickUpCalendar { get; set; }
        
        [FindsBy(How = How.ClassName, Using = "today")]
        public IWebElement PickUpDay { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"data-entrega\"]")]
        public IWebElement DropOffCalendar { get; set; }
        
        [FindsBy(How = How.XPath, Using = XPATHDROPOFFDATE2)]
        public IWebElement DropOffDay { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"hora-retirada\"]")]
        public IWebElement PickUpTime { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"estado-retirada\"]")]
        public IWebElement PickUpDestination { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"hora-entrega\"]")]
        public IWebElement DropOffTime { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"estado-entrega\"]")]
        public IWebElement DropOffDestination { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"cidade-retirada\"]")]
        public IWebElement PickUpLocation { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"cidade-entrega\"]")]
        public IWebElement DropOffLocation { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//*[@id=\"pais-de-residencia\"]")]
        public IWebElement CountryOfResidence { get; set; }
        
        [FindsBy(How = How.XPath, Using = "/html/body/section[1]/div/form/div[4]/button")]
        public IWebElement SearchRateBtn { get; set; }
        
        private IWebDriver driver;
        IWebElement source;

        public HomePage(IWebDriver driver)
        {
            this.driver = driver;
            PageFactory.InitElements(driver, this);
        }

        public void OpenPage()
        {
            driver.Navigate().GoToUrl("https://www.happytoursusa.com/en/search/show");
        }

        public void ChoosePickUpDate()
        {
            PickUpCalendar.Click();
            new WebDriverWait(driver, TimeSpan.FromSeconds(10)).Until(
                ExpectedConditions.ElementExists(By.ClassName("today")));
            PickUpDay.Click();
        }

        public void ChooseDropOffDate()
        {
            DropOffCalendar.Click();
            new WebDriverWait(driver, TimeSpan.FromSeconds(10)).Until(
                ExpectedConditions.ElementExists(By.XPath(XPATHDROPOFFDATE2)));
            driver.FindElement(By.XPath(XPATHDROPOFFDATE2)).Click();
        }

        public void ChoosePickUpTime()
        {
            PickUpTime.Click();
            PickUpTime.SendKeys(PICKUPTIME);
            PickUpTime.SendKeys(Keys.Enter);
        }

        public void ChooseDropOffTime()
        {
            DropOffTime.Click();
            DropOffTime.SendKeys(DROPOFFTIME);
            DropOffTime.SendKeys(Keys.Enter);
        }

        public void ChoosePickUpDestination()
        {
            PickUpDestination.Click();
            PickUpDestination.SendKeys(DESTINATION);
            PickUpDestination.SendKeys(Keys.Enter);
        }

        public void ChooseDropOffDestination()
        {
            DropOffDestination.Click();
            DropOffDestination.SendKeys(DESTINATION);
            DropOffDestination.SendKeys(Keys.Enter);
        }

        public void ChoosePickUpLocation()
        {
            PickUpLocation.Click();
            PickUpLocation.SendKeys(LOCATION);
            PickUpLocation.SendKeys(Keys.Enter);
        }

        public void ChooseDropOffLocation()
        {
            DropOffLocation.Click();
            DropOffLocation.SendKeys(LOCATION);
            DropOffLocation.SendKeys(Keys.Enter);
        }

        public void ChooseCountryOfResidence()
        {
            CountryOfResidence.Click();
            CountryOfResidence.SendKeys(COUNTRYOFRESIDENCE);
            CountryOfResidence.SendKeys(Keys.Enter);
        }

        public void SearchRate()
        {
            SearchRateBtn.Click();
        }

        public void WaitTime(int seconds)
        {
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(seconds);
        }

       public IWebElement FindElementByLocator(By locator)
        {
            try
            {
                source = driver.FindElement(locator);
                return source;
            }
            catch
            {
                return null;
            }
        }
    }
}