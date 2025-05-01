import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import ProduitSourceAdminTabNavigation from '../../../../../../navigation/admin/referentiel/ProduitSourceAdminTabNavigation';

const ProduitSourceAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <ProduitSourceAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default ProduitSourceAdmin;
